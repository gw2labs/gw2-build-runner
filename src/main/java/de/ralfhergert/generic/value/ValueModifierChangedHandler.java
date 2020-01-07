package de.ralfhergert.generic.value;

/**
 * Implementations of this class can register at a {@link ValueModifier} and listen
 * to changes of the modifier.
 *
 * @param <ValueType> type of the value
 * @param <ContextType> type of the object this value belongs to
 */
public interface ValueModifierChangedHandler<ValueType,ContextType> {

    void modifierHasChanged(ValueModifier<ValueType,ContextType> modifier);
}
