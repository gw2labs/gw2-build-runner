package de.ralfhergert.gw2.model;

import de.ralfhergert.generic.cloning.Copyable;
import de.ralfhergert.generic.cloning.CopyableList;

public class TrinketPiece extends Equipment<TrinketPiece> implements Copyable<TrinketPiece> {

    private final TrinketSlot trinketSlot;

    public TrinketPiece(String name, TrinketSlot trinketSlot) {
        super(Type.Trinket, name, trinketSlot.getEquipmentSlot());
        this.trinketSlot = trinketSlot;
    }

    @Override
    public TrinketPiece deepCopy() {
        return new TrinketPiece(getName(), trinketSlot)
            .setModifiers(new CopyableList<>(getModifiers()).deepCopy());
    }

    public TrinketSlot getTrinketSlot() {
        return trinketSlot;
    }
}
