package de.ralfhergert.generic.cloning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

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
