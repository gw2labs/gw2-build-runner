package de.ralfhergert.generic.cloning;

import java.util.Stack;

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
