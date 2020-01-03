package de.ralfhergert.generic.value;

public interface ValueChangedHandler<ValueType,ContextType> {

    void valueHasChanged(Value<ValueType,ContextType> value);
}
