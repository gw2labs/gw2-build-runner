package de.ralfhergert.gw2.model;

public class CharacterAttributeDescription<Type> {

    private final Class<Type> valueType;
    private final Profession profession;
    private final Type baseValue;
    private final Type capValue;
    private final String description;

    CharacterAttributeDescription(Class<Type> valueType, Profession profession, Type baseValue, Type capValue, String description) {
        this.valueType = valueType;
        this.profession = profession;
        this.baseValue = baseValue;
        this.capValue = capValue;
        this.description = description;
    }

    public Class<Type> getValueType() {
        return valueType;
    }

    /**
     * For which {@link Profession} this attributes may be valid.
     * If null this attributes in meant for all character classes.
     */
    public Profession getProfession() {
        return profession;
    }

    public Type getBaseValue() {
        return baseValue;
    }

    public Type getCapValue() {
        return capValue;
    }

    public String getDescription() {
        return description;
    }
}
