package de.ralfhergert.gw2.model.build.v1;

import de.ralfhergert.gw2.generator.ArmorPieceGenerator;
import de.ralfhergert.gw2.model.ArmorSlot;
import de.ralfhergert.gw2.model.Gw2Character;
import de.ralfhergert.gw2.model.Prefix;

import java.util.List;
import java.util.stream.Collectors;

public class ArmorConfiguration extends EquipmentConfiguration {

    private final List<ArmorSlot> armorSlots;
    private final Prefix armorPrefix;

    public ArmorConfiguration(List<ArmorSlot> armorSlots, Prefix armorPrefix) {
        super(armorSlots.stream().map(ArmorSlot::getEquipmentSlot).collect(Collectors.toList()));
        this.armorSlots = armorSlots;
        this.armorPrefix = armorPrefix;
    }

    public List<ArmorSlot> getArmorSlots() {
        return armorSlots;
    }

    public Prefix getArmorPrefix() {
        return armorPrefix;
    }

    public Gw2Character apply(Gw2Character gw2Character) {
        armorSlots.forEach(armorSlot -> gw2Character.equip(
            new ArmorPieceGenerator()
                .create(armorPrefix, gw2Character.getProfession().getArmorClass(), armorSlot)));
        return gw2Character;
    }
}
