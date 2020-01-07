package de.ralfhergert.gw2.model.build.v1;

import de.ralfhergert.gw2.generator.TrinketPieceGenerator;
import de.ralfhergert.gw2.model.Gw2Character;
import de.ralfhergert.gw2.model.Prefix;
import de.ralfhergert.gw2.model.TrinketSlot;

import java.util.List;
import java.util.stream.Collectors;

public class TrinketConfiguration extends EquipmentConfiguration {

    private final List<TrinketSlot> trinketSlots;
    private final Prefix prefix;

    public TrinketConfiguration(List<TrinketSlot> trinketSlots, Prefix prefix) {
        super(trinketSlots.stream().map(TrinketSlot::getEquipmentSlot).collect(Collectors.toList()));
        this.trinketSlots = trinketSlots;
        this.prefix = prefix;
    }

    public List<TrinketSlot> getTrinketSlots() {
        return trinketSlots;
    }

    public Prefix getPrefix() {
        return prefix;
    }

    public Gw2Character apply(Gw2Character gw2Character) {
        trinketSlots.forEach(slot -> gw2Character.equip(new TrinketPieceGenerator().create(prefix, slot)));
        return gw2Character;
    }
}
