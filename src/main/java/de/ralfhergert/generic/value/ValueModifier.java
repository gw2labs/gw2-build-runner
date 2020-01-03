package de.ralfhergert.generic.value;

import java.util.ArrayList;
import java.util.List;

public abstract class ValueModifier<ValueType,ContextType> implements Assignable<ContextType> {

    private final Object source;
    private final List<ValueModifierChangedHandler<ValueType,ContextType>> changedHandlers = new ArrayList<>();

    public ValueModifier(Object source) {
        this.source = source;
    }

    public abstract ValueType modify(ValueType value, ContextType context);

    public ValueModifier<ValueType,ContextType> addChangedHandler(ValueModifierChangedHandler<ValueType,ContextType> handler) {
        changedHandlers.add(handler);
        return this;
    }

    protected void promoteModification() {
        changedHandlers.forEach(handler -> handler.modifierHasChanged(this));
    }

    public Object getSource() {
        return source;
    }
}
