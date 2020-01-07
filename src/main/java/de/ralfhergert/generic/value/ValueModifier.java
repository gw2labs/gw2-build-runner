package de.ralfhergert.generic.value;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementations of this class are able to modify a {@link Value}. Whenever a modifier
 * want to modify the value differently a call to {@link #promoteModification()} informs
 * all dependent listeners, as the Value itself is one of them and by being informed it will
 * flag itself as being a "dirty value".
 *
 * @param <ValueType> type of the value
 * @param <ContextType> type of the object this value belongs to
 */
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
