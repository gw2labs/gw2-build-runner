package de.ralfhergert.generic.cloning;

/**
 * Marker interface for Objects able to create a deep-copy of themselves.
 * A deep-copy has no relations to its original it was being copied from.
 *
 * @param <T> Type the method {@link #deepCopy()} returns.
 */
public interface Copyable<T> {

    T deepCopy();
}
