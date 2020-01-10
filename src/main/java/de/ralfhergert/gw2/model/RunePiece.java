package de.ralfhergert.gw2.model;

import de.ralfhergert.generic.cloning.Copyable;
import de.ralfhergert.generic.cloning.CopyableList;

public class RunePiece extends Equipment<RunePiece> implements Copyable<RunePiece> {

    private final RuneSlot runeSlot;
    private final Rune rune;

    public RunePiece(RuneSlot runeSlot, Rune rune) {
        super(Type.Rune, rune.getName(), runeSlot.getEquipmentSlot());
        this.runeSlot = runeSlot;
        this.rune = rune;
    }

    @Override
    public RunePiece deepCopy() {
        return new RunePiece(runeSlot, rune)
            .setModifiers(new CopyableList<>(getModifiers()).deepCopy());
    }

    public RuneSlot getRuneSlot() {
        return runeSlot;
    }

    public Rune getRune() {
        return rune;
    }
}
