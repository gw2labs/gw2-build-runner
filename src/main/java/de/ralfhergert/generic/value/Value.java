package de.ralfhergert.generic.value;

import java.util.ArrayList;
import java.util.List;

/**
 * This value only allows to be modified by its attached modifiers.
 */
public class Value<ValueType,ContextType> {

    private final String name;
    private final ValueType startValue;

    private final List<ValueModifier<ValueType,ContextType>> modifiers = new ArrayList<>();
    private final List<ValueChangedHandler<ValueType,ContextType>> changedHandlers = new ArrayList<>();

    private boolean isModified = false;
    private ValueType finalValue;

    public Value(String name, ValueType startValue) {
        this.name = name;
        this.startValue = startValue;
        this.finalValue = startValue;
    }

    public String getName() {
        return name;
    }

    public ValueType getValue(ContextType context) {
        if (isModified) {
            finalValue = startValue;
            modifiers.forEach(modifier -> finalValue = modifier.modify(finalValue, context));
            isModified = false;
        }
        return finalValue;
    }

    public void markAsModified() {
        isModified = true;
        /* operate on a copy of the list to avoid ConcurrentModificationException
         * when a listener want to remove itself for the list. */
        new ArrayList<>(changedHandlers).forEach(handler -> handler.valueHasChanged(this));
    }

    private void markAsModified(ValueModifier<ValueType,ContextType> modifier) {
        if (modifiers.contains(modifier)) {
            isModified = true;
            /* operate on a copy of the list to avoid ConcurrentModificationException
             * when a listener want to remove itself for the list. */
            new ArrayList<>(changedHandlers).forEach(handler -> handler.valueHasChanged(this));
        }
    }

    public Value<ValueType,ContextType> addModifier(ValueModifier<ValueType,ContextType> modifier) {
        modifiers.add(modifier);
        modifier.addChangedHandler(this::markAsModified);
        markAsModified(modifier);
        return this;
    }

    public Value<ValueType,ContextType> removeModifier(ValueModifier<ValueType,ContextType> modifier) {
        markAsModified(modifier);
        modifiers.remove(modifier);
        return this;
    }

    public Value<ValueType,ContextType> addChangedHandler(ValueChangedHandler<ValueType,ContextType> handler) {
        changedHandlers.add(handler);
        return this;
    }

    public Value<ValueType,ContextType> removeChangedHandler(ValueChangedHandler<ValueType,ContextType> handler) {
        changedHandlers.remove(handler);
        return this;
    }
}
