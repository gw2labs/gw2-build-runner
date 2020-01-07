package de.ralfhergert.generic.value;

/**
 * Implementation of this interface can be triggered whenever a {@link Value} changes.
 *
 * @param <ValueType> type of the value
 * @param <ContextType> type of the object this value belongs to
 */
public interface ValueChangedHandler<ValueType,ContextType> {

    void valueHasChanged(Value<ValueType,ContextType> value);
}
