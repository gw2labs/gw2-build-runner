package de.ralfhergert.gw2.model;

public class ArmorPiece extends Equipment<ArmorPiece> {

    private final ArmorClass armorClass;
    private final ArmorSlot armorSlot;

    public ArmorPiece(String name, ArmorClass armorClass, ArmorSlot armorSlot) {
        super(Type.Armor, name, armorSlot.getEquipmentSlot());
        this.armorClass = armorClass;
        this.armorSlot = armorSlot;
    }

    public ArmorClass getArmorClass() {
        return armorClass;
    }

    public ArmorSlot getArmorSlot() {
        return armorSlot;
    }
}
