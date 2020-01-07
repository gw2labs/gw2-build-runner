package de.ralfhergert.generic.cloning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Implementation of a list which can create a copy of itself thereby creating copies
 * of all elements in this list. This requires that all elements of this list implement
 * {@link Copyable} as well.
 *
 * @param <T> Type of elements this list accepts. They have to implement {@link Copyable}.
 */
public class CopyableList<T extends Copyable<T>> extends ArrayList<T> implements Copyable<CopyableList<T>> {

    public CopyableList() {}

    public CopyableList(Collection<T> c) {
        super(c);
    }

    @Override
    public CopyableList<T> deepCopy() {
        return this.stream()
            .map(Copyable::deepCopy)
            .collect(Collectors.toCollection(CopyableList::new));
    }
}
