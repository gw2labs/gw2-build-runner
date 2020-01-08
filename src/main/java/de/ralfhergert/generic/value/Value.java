package de.ralfhergert.generic.value;

import java.util.ArrayList;
import java.util.List;

/**
 * This value only allows to be modified by its attached modifiers.
 *
 * @param <ValueType> type of the value itself
 * @param <OwnerType> type of the object this value belongs to
 */
public class Value<ValueType,OwnerType> {

    private final String name;
    private final ValueType startValue;
    private final OwnerType owner;

    private final List<ValueModifier<ValueType,OwnerType>> modifiers = new ArrayList<>();
    private final List<ValueChangedHandler<ValueType,OwnerType>> changedHandlers = new ArrayList<>();

    private boolean isModified = false;
    private ValueType finalValue;

    public Value(String name, ValueType startValue, OwnerType owner) {
        this.name = name;
        this.startValue = startValue;
        this.finalValue = startValue;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public ValueType getValue() {
        if (isModified) {
            finalValue = startValue;
            modifiers.forEach(modifier -> finalValue = modifier.modify(finalValue, owner));
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

    private void markAsModified(ValueModifier<ValueType,OwnerType> modifier) {
        if (modifiers.contains(modifier)) {
            isModified = true;
            /* operate on a copy of the list to avoid ConcurrentModificationException
             * when a listener want to remove itself for the list. */
            new ArrayList<>(changedHandlers).forEach(handler -> handler.valueHasChanged(this));
        }
    }

    public Value<ValueType,OwnerType> addModifier(ValueModifier<ValueType,OwnerType> modifier) {
        modifiers.add(modifier);
        modifier.addChangedHandler(this::markAsModified);
        markAsModified(modifier);
        return this;
    }

    public Value<ValueType,OwnerType> removeModifier(ValueModifier<ValueType,OwnerType> modifier) {
        markAsModified(modifier);
        modifiers.remove(modifier);
        return this;
    }

    public Value<ValueType,OwnerType> addChangedHandler(ValueChangedHandler<ValueType,OwnerType> handler) {
        changedHandlers.add(handler);
        return this;
    }

    public Value<ValueType,OwnerType> removeChangedHandler(ValueChangedHandler<ValueType,OwnerType> handler) {
        changedHandlers.remove(handler);
        return this;
    }
}
