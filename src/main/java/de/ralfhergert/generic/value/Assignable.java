package de.ralfhergert.generic.value;

/**
 * Marker interface for objects which can be assigned to other objects.
 *
 * @param <Target> object class this assignable can be assigned to.
 */
public interface Assignable<Target> {

    void assignTo(Target target);

    void resignFrom(Target target);
}
