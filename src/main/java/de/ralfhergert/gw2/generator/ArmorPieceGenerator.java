package de.ralfhergert.gw2.generator;

import de.ralfhergert.gw2.model.*;
import de.ralfhergert.gw2.modifier.CharacterAttributeModifier;

import java.util.stream.Collectors;

public class ArmorPieceGenerator {

    public ArmorPiece create(Prefix prefix, ArmorClass armorClass, ArmorSlot slot) {
        return new ArmorPiece(prefix.getArmorName(), armorClass, slot)
            .addModifier(new CharacterAttributeModifier(
                CharacterAttribute.Defense,
                DefenseRating.findDefenseRatingFor(Rarity.Ascended, armorClass, slot)))
            .addModifiers(prefix.getModifiers().stream()
                .map(mod -> new CharacterAttributeModifier(mod.getKey(), (int)Math.floor(slot.getPrefixScaling() * mod.getIncrease().orElse(0).intValue())))
                .collect(Collectors.toList()));
    }
}
