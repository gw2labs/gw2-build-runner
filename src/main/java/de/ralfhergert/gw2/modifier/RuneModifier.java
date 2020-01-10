package de.ralfhergert.gw2.modifier;

import de.ralfhergert.generic.value.Assignable;
import de.ralfhergert.generic.value.ValueChangedHandler;
import de.ralfhergert.gw2.model.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RuneModifier implements Assignable<Gw2Character> {

    private final ValueChangedHandler<Equipment[],Gw2Character> equipmentListener = (v) -> checkEquippedRunes(v.getValue(), v.getOwner());

    private final List<InstalledRuneModifier> installedRuneModifiers = new ArrayList<>();

    private void checkEquippedRunes(Equipment[] equipmentPieces, Gw2Character gw2Character) {
        // find all currently equipped runes.
        final Map<Rune, List<RunePiece>> currentlyEquippedRunes = Stream.of(equipmentPieces)
            .filter(equipment -> equipment instanceof RunePiece)
            .map(equipment -> (RunePiece)equipment)
            .collect(Collectors.groupingBy(RunePiece::getRune));

        // calculate which rune sources are expected to be present
        final Set<RuneSource> expectedRuneSources = currentlyEquippedRunes.entrySet().stream()
            .flatMap(entry -> IntStream.range(0, entry.getValue().size()).mapToObj(i -> new RuneSource(entry.getKey(), i)))
            .collect(Collectors.toSet());

        // uninstall all modifiers which no longer should be installed.
        new ArrayList<>(installedRuneModifiers).stream()
            .filter(mod -> !expectedRuneSources.contains(mod.getRuneSource()))
            .forEach(this::uninstall);

        // install missing rune modifiers.
        final Set<RuneSource> installedRuneSources = installedRuneModifiers.stream()
            .map(InstalledRuneModifier::getRuneSource)
            .collect(Collectors.toSet());

        expectedRuneSources.stream()
            .filter(runeSource -> !installedRuneSources.contains(runeSource))
            .forEach(runeSource -> install(runeSource, gw2Character));
    }

    private void install(RuneSource runeSource, Gw2Character gw2Character) {
        InstalledRuneModifier mod = new InstalledRuneModifier(runeSource, runeSource.getBonus(), gw2Character);
        installedRuneModifiers.add(mod);
        mod.install();
    }

    private void uninstall(InstalledRuneModifier installedRuneModifier) {
        if (installedRuneModifiers.remove(installedRuneModifier)) {
            installedRuneModifier.uninstall();
        }
    }

    @Override
    public void assignTo(Gw2Character gw2Character) {
        gw2Character.getAttribute(CharacterAttribute.Equipment).addChangedHandler(equipmentListener);
    }

    @Override
    public void resignFrom(Gw2Character gw2Character) {
        gw2Character.getAttribute(CharacterAttribute.Equipment).removeChangedHandler(equipmentListener);
    }

    public static class RuneSource {

        private final Rune rune;
        private final int tier;

        public RuneSource(Rune rune, int tier) {
            this.rune = rune;
            this.tier = tier;
        }

        public Rune getRune() {
            return rune;
        }

        public int getTier() {
            return tier;
        }

        public Assignable<Gw2Character> getBonus() {
            return rune.getBonus()[tier];
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            RuneSource that = (RuneSource)o;
            return tier == that.tier && rune == that.rune;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rune, tier);
        }
    }

    public static class InstalledRuneModifier {

        private final RuneSource runeSource;
        private final Assignable<Gw2Character> assignable;
        private final Gw2Character gw2Character;

        public InstalledRuneModifier(RuneSource runeSource, Assignable<Gw2Character> assignable, Gw2Character gw2Character) {
            this.runeSource = runeSource;
            this.assignable = assignable;
            this.gw2Character = gw2Character;
        }

        public RuneSource getRuneSource() {
            return runeSource;
        }

        public Assignable<Gw2Character> getAssignable() {
            return assignable;
        }

        public void install() {
            assignable.assignTo(gw2Character);
        }

        public void uninstall() {
            assignable.resignFrom(gw2Character);
        }
    }
}
