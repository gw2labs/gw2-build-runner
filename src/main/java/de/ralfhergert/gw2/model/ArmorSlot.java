package de.ralfhergert.gw2.model;

public enum ArmorSlot {
    Head(0.4478, EquipmentSlot.Head),
    Shoulders(0.3367, EquipmentSlot.Shoulders),
    Chest(1.0, EquipmentSlot.Chest),
    Hands(0.3367, EquipmentSlot.Hands),
    Legs(0.6695, EquipmentSlot.Legs),
    Feet(0.3367, EquipmentSlot.Feet);
    // Ignore BreathingApparatus(0, EquipmentSlot.BreathingApparatus);

    private final double prefixScaling;
    private final EquipmentSlot equipmentSlot;

    ArmorSlot(double prefixScaling, EquipmentSlot equipmentSlot) {
        this.prefixScaling = prefixScaling;
        this.equipmentSlot = equipmentSlot;
    }

    /**
     * The values used in {@link Prefix} define the value of the chest piece.
     */
    public double getPrefixScaling() {
        return prefixScaling;
    }

    public EquipmentSlot getEquipmentSlot() {
        return equipmentSlot;
    }
}
