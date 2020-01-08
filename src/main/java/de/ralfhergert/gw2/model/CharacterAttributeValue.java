package de.ralfhergert.gw2.model;

import de.ralfhergert.generic.value.Value;

import java.util.function.Supplier;

public class CharacterAttributeValue<Type> extends Value<Type,Gw2Character> {

    private final CharacterAttribute key;

    private Type capValue; // can be null
    /** This accessor can be a facade to implement value capping. */
    private Supplier<Type> valueAccessor = super::getValue;

    public CharacterAttributeValue(CharacterAttribute key, Gw2Character gw2Character) {
        this(key, (Type)key.getBaseValue(), gw2Character);
    }

    public CharacterAttributeValue(CharacterAttribute key, Type baseValue, Gw2Character gw2Character) {
        super(key.name(), baseValue, gw2Character);
        this.key = key;
    }

    public Type getCapValue() {
        return capValue;
    }

    public CharacterAttributeValue setCapValue(Type capValue) {
        this.capValue = capValue;
        if (capValue == null) {
            valueAccessor = super::getValue;
        } else {
            valueAccessor = this::getCappedValue;
        }
        return this;
    }

    private Type getCappedValue() {
        final Type value = super.getValue();
        if (capValue == null) {
            return value;
        }
        if (value instanceof Integer) {
            return (Type)key.getValueType().cast(Math.min((Integer)value, (Integer)capValue));
        } else if (value instanceof Double){
            return (Type)key.getValueType().cast(Math.min((Double)value, (Double)capValue));
        } else {
            throw new IllegalArgumentException("could not cap value " + value);
        }
    }

    @Override
    public Type getValue() {
        return valueAccessor.get();
    }
}
