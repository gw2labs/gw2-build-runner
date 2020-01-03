package de.ralfhergert.gw2.runner;

import ch.qos.logback.classic.Level;
import de.ralfhergert.gw2.api.v2.*;
import de.ralfhergert.gw2.model.Profession;
import de.ralfhergert.gw2.model.Specialization;
import de.ralfhergert.gw2.model.Trait;
import de.ralfhergert.gw2.model.TraitSlot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class queries the Guild Wars 2 API to check for changes in the
 * professions, specializations, traits and skills.
 */
public class CheckForChanges implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(CheckForChanges.class);

    private final boolean stopOnError = false;
    private final List<ValidationIssue> issues = new ArrayList<>();

    public static void main(String... args) {
        LOG.info("Checking for changes");

        if (Arrays.asList(args).contains("--debug")) {
            trySettingDebugLogLevel();
        }
        if (Arrays.asList(args).contains("--trace")) {
            trySettingTraceLogLevel();
        }
        LOG.debug("debug logging enabled");
        LOG.trace("trace logging enabled");

        CheckForChanges checker = new CheckForChanges();
        checker.run();

        checker.getIssues().forEach(issue -> LOG.error(issue.toString()));

        if (checker.getIssues().isEmpty()) {
            LOG.info("OK: no issues with data found");
        } else {
            LOG.error("FAILED: " + checker.getIssues().size() + " issues found.");
        }

        System.exit(checker.getIssues().size());
    }

    private static void trySettingDebugLogLevel() {
        if (LOG instanceof ch.qos.logback.classic.Logger) {
            ((ch.qos.logback.classic.Logger)LOG).setLevel(Level.DEBUG);
        }
    }

    private static void trySettingTraceLogLevel() {
        if (LOG instanceof ch.qos.logback.classic.Logger) {
            ((ch.qos.logback.classic.Logger)LOG).setLevel(Level.TRACE);
        }
    }

    @Override
    public void run() {
        final List<Profession> professionsToCheck = checkProfessions(new GetAllProfessions().get(), issues::add);
        LOG.debug("checking {} professions", professionsToCheck. size());
        if (stopOnError && !issues.isEmpty()) {
            return;
        }
        for (Profession profession : professionsToCheck) {
            checkProfession(new GetProfession(profession.name()).get(), issues::add);
        }

        final List<Specialization> specializationsToCheck = checkSpecializations(new GetAllSpecializations().get(), issues::add);
        LOG.debug("checking {} specializations", specializationsToCheck. size());
        for (Specialization specialization : specializationsToCheck) {
            checkSpecialization(new GetSpecialization(specialization.getId()).get(), issues::add);
        }

        final List<Trait> traitsToCheck = checkTraits(new GetAllTraits().get(), issues::add);
        LOG.debug("checking {} traits", traitsToCheck. size());
    }

    public List<ValidationIssue> getIssues() {
        return issues;
    }

    private List<Profession> checkProfessions(final List<String> professionNames, Consumer<ValidationIssue> issueHandler) {
        // search unknown profession names
        professionNames.stream()
            .filter(name -> {
                try {
                    Profession.valueOf(name);
                    return false;
                } catch (IllegalArgumentException e) {
                    return true;
                }
            })
            .map(name -> new ValidationIssue("unknown profession with name '" + name + "' found"))
            .forEach(issueHandler);

        // search obsolete profession names
        Stream.of(Profession.values())
            .map(Profession::name)
            .filter(Predicate.not(professionNames::contains))
            .map(name -> new ValidationIssue("profession with name '" + name + "' is no longer supported"))
            .forEach(issueHandler);

        // return all matching professions
        return Stream.of(Profession.values())
            .filter(profession -> professionNames.contains(profession.name()))
            .collect(Collectors.toList());
    }

    private void checkProfession(Gw2Profession gw2Profession, Consumer<ValidationIssue> issueHandler) {
        // search for unknown specializations
        gw2Profession.getSpecializations().stream()
            .filter(specializationId -> Specialization.getById(specializationId) == null)
            .map(specializationId -> new ValidationIssue("unknown specialization '" + specializationId + "' in profession with name '" + gw2Profession.getId() + "' found"))
            .forEach(issueHandler);

        // search for obsolete specializations
        Specialization.getAllFor(Profession.valueOf(gw2Profession.getId()))
            .filter(specialization -> !gw2Profession.getSpecializations().contains(specialization.getId()))
            .map(specializationId -> new ValidationIssue("specialization '" + specializationId + "' in profession with name '" + gw2Profession.getId() + "' is no longer supported"))
            .forEach(issueHandler);
    }

    private List<Specialization> checkSpecializations(final List<Integer> specializationIds, Consumer<ValidationIssue> issueHandler) {
        // search unknown specializations
        specializationIds.stream()
            .filter(id -> Specialization.getById(id) == null)
            .map(id -> new ValidationIssue("unknown specialization with id '" + id + "' found"))
            .forEach(issueHandler);

        // search obsolete specializations
        Stream.of(Specialization.values())
            .map(Specialization::getId)
            .filter(Predicate.not(specializationIds::contains))
            .map(id -> new ValidationIssue("specialization with id '" + id + "' is no longer supported"))
            .forEach(issueHandler);

        // return all matching specializations
        return Stream.of(Specialization.values())
            .filter(specialization -> specializationIds.contains(specialization.getId()))
            .collect(Collectors.toList());
    }

    private void checkSpecialization(Gw2Specialization gw2Specialization, Consumer<ValidationIssue> issueHandler) {
        Specialization specialization = Specialization.getById(gw2Specialization.getId());
        if (specialization == null) {
            issueHandler.accept(new ValidationIssue("specialization with id '" + gw2Specialization.getId() + "' is unknown"));
            return;
        }
        if (!Objects.equals(specialization.getName(), gw2Specialization.getName())) {
            issueHandler.accept(new ValidationIssue("name of specialization with id '" + gw2Specialization.getId() + "' is expected to be '" + gw2Specialization.getName() + "' but was '" + specialization.getName() + "'"));
        }
        if (specialization.getProfession() != gw2Specialization.getProfession()) {
            issueHandler.accept(new ValidationIssue("specialization with id '" + gw2Specialization.getId() + "' is expected to belong to '" + gw2Specialization.getProfession().name() + "' but was is assigned to '" + specialization.getProfession().name() + "'"));
        }
        if (specialization.isElite() != gw2Specialization.isElite()) {
            issueHandler.accept(new ValidationIssue("elite-status of specialization with id '" + gw2Specialization.getId() + "' is expected to be '" + gw2Specialization.isElite() + "' but was '" + specialization.isElite() + "'"));
        }

        // validate all traits of that specialization
        Stream.concat(gw2Specialization.getMinorTraits().stream(), gw2Specialization.getMajorTraits().stream())
            .forEach(traitId -> {
                Trait trait = Trait.getById(traitId);
                if (trait == null) {
                    issueHandler.accept(new ValidationIssue("trait with id '" + traitId + "' is unknown (was requested by specialization '" + gw2Specialization.getId() + "')"));
                    return;
                }
                if (trait.getSpecialization().getId() != gw2Specialization.getId()) {
                    issueHandler.accept(new ValidationIssue("trait with id '" + traitId + "' should belong to specialization '" + gw2Specialization.getId() + "') but was assigned to '" + trait.getSpecialization().getId() + "'"));
                }
            });

        for (int traitId : gw2Specialization.getMinorTraits()) {
            Trait trait = Trait.getById(traitId);
            if (trait != null && trait.getSlot() != TraitSlot.Minor) {
                issueHandler.accept(new ValidationIssue("trait with id '" + traitId + "' should be a minor trait in specialization '" + gw2Specialization.getId() + "')"));
            }
        }

        for (int traitId : gw2Specialization.getMajorTraits()) {
            Trait trait = Trait.getById(traitId);
            if (trait != null && trait.getSlot() != TraitSlot.Major) {
                issueHandler.accept(new ValidationIssue("trait with id '" + traitId + "' should be a major trait in specialization '" + gw2Specialization.getId() + "')"));
            }
        }
    }

    private List<Trait> checkTraits(final Collection<Integer> traitIds, Consumer<ValidationIssue> issueHandler) {
        // search unknown specializations
        traitIds.stream()
            .filter(id -> Trait.getById(id) == null)
            .map(id -> {
                final Gw2Trait gw2Trait = new GetTrait(id).get();
                return new ValidationIssue("unknown trait with id '" + id + "' found: " + gw2Trait.getName()
                    + "("+id+", "+Specialization.getById(gw2Trait.getSpecialization()).name()+", "
                    + gw2Trait.getTier() +", "+ gw2Trait.getOrder() +", " + gw2Trait.getSlot().name() +", \""+gw2Trait.getName()+"\"),");
            })
            .forEach(issueHandler);

        // search obsolete specializations
        Stream.of(Trait.values())
            .map(Trait::getId)
            .filter(Predicate.not(traitIds::contains))
            .map(id -> new ValidationIssue("trait with id '" + id + "' is no longer supported"))
            .forEach(issueHandler);

        // return all matching specializations
        return Stream.of(Trait.values())
            .filter(trait -> traitIds.contains(trait.getId()))
            .collect(Collectors.toList());
    }
}
