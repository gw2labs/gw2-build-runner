package de.ralfhergert.generic.value;

public interface ValueModifierChangedHandler<ValueType,ContextType> {

    void modifierHasChanged(ValueModifier<ValueType,ContextType> modifier);
}
