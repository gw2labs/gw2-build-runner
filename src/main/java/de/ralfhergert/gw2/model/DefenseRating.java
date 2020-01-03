package de.ralfhergert.gw2.model;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

/**
 * These defense ratings can be be found here https://wiki.guildwars2.com/wiki/Armor.
 */
public enum DefenseRating {
    DR_ASC_HEAVY_HEAD(Rarity.Ascended, ArmorClass.Heavy, ArmorSlot.Head, 127),
    DR_ASC_HEAVY_SHOULDERS(Rarity.Ascended, ArmorClass.Heavy, ArmorSlot.Shoulders, 127),
    DR_ASC_HEAVY_CHEST(Rarity.Ascended, ArmorClass.Heavy, ArmorSlot.Chest, 381),
    DR_ASC_HEAVY_GLOVES(Rarity.Ascended, ArmorClass.Heavy, ArmorSlot.Hands, 191),
    DR_ASC_HEAVY_LEGGINGS(Rarity.Ascended, ArmorClass.Heavy, ArmorSlot.Legs, 254),
    DR_ASC_HEAVY_BOOTS(Rarity.Ascended, ArmorClass.Heavy, ArmorSlot.Feet, 191),
    DR_ASC_MEDIUM_HEAD(Rarity.Ascended, ArmorClass.Medium, ArmorSlot.Head, 102),
    DR_ASC_MEDIUM_SHOULDERS(Rarity.Ascended, ArmorClass.Medium, ArmorSlot.Shoulders, 102),
    DR_ASC_MEDIUM_CHEST(Rarity.Ascended, ArmorClass.Medium, ArmorSlot.Chest, 355),
    DR_ASC_MEDIUM_GLOVES(Rarity.Ascended, ArmorClass.Medium, ArmorSlot.Hands, 165),
    DR_ASC_MEDIUM_LEGGINGS(Rarity.Ascended, ArmorClass.Medium, ArmorSlot.Legs, 229),
    DR_ASC_MEDIUM_BOOTS(Rarity.Ascended, ArmorClass.Medium, ArmorSlot.Feet, 165),
    DR_ASC_LIGHT_HEAD(Rarity.Ascended, ArmorClass.Light, ArmorSlot.Head, 77),
    DR_ASC_LIGHT_SHOULDERS(Rarity.Ascended, ArmorClass.Light, ArmorSlot.Shoulders, 77),
    DR_ASC_LIGHT_CHEST(Rarity.Ascended, ArmorClass.Light, ArmorSlot.Chest, 330),
    DR_ASC_LIGHT_GLOVES(Rarity.Ascended, ArmorClass.Light, ArmorSlot.Hands, 140),
    DR_ASC_LIGHT_LEGGINGS(Rarity.Ascended, ArmorClass.Light, ArmorSlot.Legs, 203),
    DR_ASC_LIGHT_BOOTS(Rarity.Ascended, ArmorClass.Light, ArmorSlot.Feet, 140);

    private final Rarity rarity;
    private final ArmorClass armorClass;
    private final ArmorSlot armorSlot;
    private final int value;

    DefenseRating(Rarity rarity, ArmorClass armorClass, ArmorSlot armorSlot, int value) {
        this.rarity = rarity;
        this.armorClass = armorClass;
        this.armorSlot = armorSlot;
        this.value = value;
    }

    public static int findDefenseRatingFor(Rarity rarity, ArmorClass armorClass, ArmorSlot armorSlot) {
        return Stream.of(values())
            .filter(rating -> rating.getArmorSlot() == armorSlot)
            .filter(rating -> rating.getArmorClass() == armorClass)
            .filter(rating -> rating.getRarity() == rarity)
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("Could not find defense rating for " + rarity + ", " + armorClass + ", " + armorSlot + "."))
            .getValue();
    }

    public Rarity getRarity() {
        return rarity;
    }

    public ArmorClass getArmorClass() {
        return armorClass;
    }

    public ArmorSlot getArmorSlot() {
        return armorSlot;
    }

    public int getValue() {
        return value;
    }
}
