package de.ralfhergert.gw2.model.build.v1;

import de.ralfhergert.gw2.model.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A BuildId version 1 has to start with the literal "GW2L1-". The buildId can
 * be split by '-' into blocks. Each block start with a block-identifier-label,
 * followed by a colon (:), follow by the block's content. A block can be split
 * by ';' into sub-block. Each sub-block is block.
 *
 * buildId          ::= 'GW2L1-' [ blocks ]
 * blocks           ::= block [ '-' blocks ]
 * block            ::= block-identifier ':' block-content
 * block-identifier ::= 'p' |       ; profession
 *                      'e'         ; equipment
 * block-content    ::= content | sub-blocks
 * sub-blocks       ::= block [ ';' block ]
 */
public class BuildId {

    private static final String PREAMBLE = "GW2L1-";

    public Gw2Character createFrom(final String buildId) {
        Objects.requireNonNull(buildId, "buildId must not be null");
        if (!buildId.startsWith(PREAMBLE)) {
            throw new IllegalArgumentException("given buildId is not a schema version 1 id");
        }
        final List<ConfigurationBlock> blocks = Arrays.stream(buildId.substring(PREAMBLE.length()).split("-"))
            .flatMap(new ConfigurationBlockParser())
            .collect(Collectors.toList());

        final Profession profession = readProfession(Collections.unmodifiableList(blocks));
        final List<EquipmentConfiguration> equipmentConfigurations = new ArrayList<>();
        equipmentConfigurations.addAll(readArmorConfigurations(Collections.unmodifiableList(blocks)));
        equipmentConfigurations.addAll(readTrinketConfigurations(Collections.unmodifiableList(blocks)));

        validateEquipmentConfigurationForDuplicateSlots(equipmentConfigurations);

        Gw2Character gw2Character = new Gw2Character(profession);
        equipmentConfigurations.forEach(config -> config.apply(gw2Character));

        return gw2Character;
    }

    /**
     * This method uses all blocks starting with "p:"
     */
    private Profession readProfession(Collection<ConfigurationBlock> blocks) {
        // preliminary check
        if (blocks.stream().noneMatch(block -> block.getIdentifier().equals(("p")))) {
            throw new IllegalArgumentException("buildId does not contain a profession definition. Please add a block like for instance \"p:Guardian\".");
        }

        final Set<Profession> professions = blocks.stream()
            .filter(block -> block.getIdentifier().equals("p"))
            .map(ConfigurationBlock::getContent)
            .flatMap(prefix -> Arrays.stream(Profession.values())
                .filter(profession -> profession.name().startsWith(prefix))
            )
            .collect(Collectors.toSet());

        // validation
        if (professions.isEmpty()) {
            throw new IllegalArgumentException("profession in buildId could not be mapped to one of " + Arrays.toString(Profession.values()) + ". Is the profession misspelled?");
        } else if (professions.size() > 1) {
            throw new IllegalArgumentException("multiple professions " + professions.toString() + " found in buildId. There should be only one.");
        } else {
            return professions.iterator().next();
        }
    }

    /**
     * This method uses all blocks starting in "e:a:".
     */
    private List<ArmorConfiguration> readArmorConfigurations(Collection<ConfigurationBlock> blocks) {
        return new ConfigurationBlockMapper<>("e:a", new ArmorConfigurationParser())
            .apply(blocks.stream())
            .collect(Collectors.toList());
    }

    /**
     * This method uses all blocks starting in "e:t:". (short for equipment trinkets)
     */
    private List<TrinketConfiguration> readTrinketConfigurations(Collection<ConfigurationBlock> blocks) {
        return new ConfigurationBlockMapper<>("e:t", new TrinketConfigurationParser())
            .apply(blocks.stream())
            .collect(Collectors.toList());
    }

    private void validateEquipmentConfigurationForDuplicateSlots(Collection<EquipmentConfiguration> configs) {
        final List<EquipmentSlot> duplicateSlots = configs.stream()
            .flatMap(config -> config.getSlots().stream())
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet()
            .stream()
            .filter(entry -> entry.getValue() > 1)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        if (!duplicateSlots.isEmpty()) {
            throw new IllegalArgumentException("multiple configurations for equipment slots " + duplicateSlots.toString() + " have been found.");
        }
    }
}
