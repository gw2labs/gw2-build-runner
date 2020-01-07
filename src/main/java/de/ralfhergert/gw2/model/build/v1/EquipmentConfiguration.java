package de.ralfhergert.gw2.model.build.v1;

import de.ralfhergert.gw2.model.*;

import java.util.List;

public abstract class EquipmentConfiguration {

    private final List<EquipmentSlot> slots;

    public EquipmentConfiguration(List<EquipmentSlot> slots) {
        this.slots = slots;
    }

    public List<EquipmentSlot> getSlots() {
        return slots;
    }

    public abstract Gw2Character apply(Gw2Character gw2Character);
}
