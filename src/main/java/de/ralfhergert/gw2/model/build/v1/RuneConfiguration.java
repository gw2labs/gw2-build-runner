package de.ralfhergert.gw2.model.build.v1;

import de.ralfhergert.gw2.model.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class contains the configuration which rune to use read from a given {@link BuildId}.
 */
public class RuneConfiguration extends EquipmentConfiguration {

    private final List<RuneSlot> runeSlots;
    private final Rune rune;

    public RuneConfiguration(List<RuneSlot> runeSlots, Rune rune) {
        super(runeSlots.stream().map(RuneSlot::getEquipmentSlot).collect(Collectors.toList()));
        this.runeSlots = runeSlots;
        this.rune = rune;
    }

    public List<RuneSlot> getRuneSlots() {
        return runeSlots;
    }

    public Rune getRune() {
        return rune;
    }

    public Gw2Character apply(Gw2Character gw2Character) {
        runeSlots.forEach(slot -> gw2Character.equip(new RunePiece(slot, rune)));
        return gw2Character;
    }
}
