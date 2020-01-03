package de.ralfhergert.gw2.model;

public enum Profession {
    Guardian(ArmorClass.Heavy, 1645),
    Revenant(ArmorClass.Heavy, 5922),
    Warrior(ArmorClass.Heavy, 9212),
    Engineer(ArmorClass.Medium, 5922),
    Ranger(ArmorClass.Medium, 5922),
    Thief(ArmorClass.Medium, 1645),
    Elementalist(ArmorClass.Light, 1645),
    Mesmer(ArmorClass.Light, 5922),
    Necromancer(ArmorClass.Light, 9212);

    private final ArmorClass armorClass;
    private final int baseHealth;

    Profession(ArmorClass armorClass, int baseHealth) {
        this.armorClass = armorClass;
        this.baseHealth = baseHealth;
    }

    public ArmorClass getArmorClass() {
        return armorClass;
    }

    public int getBaseHealth() {
        return baseHealth;
    }
}
