package de.ralfhergert.generic.value;

public interface Assignable<Target> {

    void assignTo(Target target);

    void resignFrom(Target target);
}
