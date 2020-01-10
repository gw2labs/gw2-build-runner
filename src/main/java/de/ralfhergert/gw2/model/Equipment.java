package de.ralfhergert.gw2.model;

import de.ralfhergert.gw2.modifier.CharacterAttributeModifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Equipment<Self extends Equipment<Self>> {

    public enum Type {
        Armor,
        Enhancement,
        Nourishment,
        Rune,
        Trinket,
        Weapon
    }

    private Type type;
    private String name;
    private EquipmentSlot slot;
    private final List<CharacterAttributeModifier> modifiers = new ArrayList<>();

    public Equipment(Type type, String name, EquipmentSlot slot) {
        this.type = type;
        this.name = name;
        this.slot = slot;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public EquipmentSlot getSlot() {
        return slot;
    }

    public List<CharacterAttributeModifier> getModifiers() {
        return modifiers;
    }

    public Self setModifiers(Collection<CharacterAttributeModifier> modifiers) {
        this.modifiers.clear();
        this.modifiers.addAll(modifiers);
        return (Self)this;
    }

    public Self addModifier(CharacterAttributeModifier modifier) {
        this.modifiers.add(modifier);
        return (Self)this;
    }

    public Self addModifiers(Collection<CharacterAttributeModifier> modifiers) {
        this.modifiers.addAll(modifiers);
        return (Self)this;
    }
}
