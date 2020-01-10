package de.ralfhergert.gw2.model;

/**
 * This enumeration specifies in which slots runes can be placed.
 */
public enum RuneSlot {
    Head(EquipmentSlot.HeadRune),
    Shoulders(EquipmentSlot.ShouldersRune),
    Chest(EquipmentSlot.ChestRune),
    Hands(EquipmentSlot.HandsRune),
    Legs(EquipmentSlot.LegsRune),
    Feet(EquipmentSlot.FeetRune);
    // ignore the BreathingApparatus since underwater combat is not jet supported.

    private final EquipmentSlot equipmentSlot;

    RuneSlot(EquipmentSlot equipmentSlot) {
        this.equipmentSlot = equipmentSlot;
    }

    public EquipmentSlot getEquipmentSlot() {
        return equipmentSlot;
    }
}
