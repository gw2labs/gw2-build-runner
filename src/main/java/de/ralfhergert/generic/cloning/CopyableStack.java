package de.ralfhergert.generic.cloning;

import java.util.Stack;

/**
 * Implementation of a stack which can create a copy of itself thereby creating copies
 * of all elements in this stack. This requires that all elements of this stack implement
 * {@link Copyable} as well.
 *
 * @param <T> Type of elements this stack accepts. They have to implement {@link Copyable}.
 */
public class CopyableStack<T extends Copyable<T>> extends Stack<T> implements Copyable<CopyableStack<T>> {

    public CopyableStack() {}

    @Override
    public CopyableStack<T> deepCopy() {
        final CopyableStack<T> stack = new CopyableStack<>();
        this.stream()
            .map(Copyable::deepCopy)
            .forEach(stack::add);
        return stack;
    }
}
