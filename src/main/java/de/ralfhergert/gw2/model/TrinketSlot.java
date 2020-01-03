package de.ralfhergert.gw2.model;

public enum TrinketSlot {
    Neck(1.099, EquipmentSlot.Neck),
    EarLeft(0.78, EquipmentSlot.EarLeft),
    EarRight(0.78, EquipmentSlot.EarRight),
    FingerLeft(0.893, EquipmentSlot.FingerLeft),
    FingerRight(0.893, EquipmentSlot.FingerRight),
    Back(0.429, EquipmentSlot.Back);

    private final double prefixScaling;
    private final EquipmentSlot equipmentSlot;

    TrinketSlot(double prefixScaling, EquipmentSlot equipmentSlot) {
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
