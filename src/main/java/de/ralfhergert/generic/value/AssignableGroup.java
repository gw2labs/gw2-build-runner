package de.ralfhergert.generic.value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * This group allow to bundle multiple {@link Assignable} and assign them with one call.
 *
 * @param <Target> object type the assignable can be assigned to.
 */
public class AssignableGroup<Target> implements Assignable<Target> {

    private final List<Assignable<Target>> assignables = new ArrayList<>();

    public AssignableGroup(Collection<Assignable<Target>> assignables) {
        this.assignables.addAll(assignables);
    }

    public AssignableGroup(Assignable<Target>... assignables) {
        this.assignables.addAll(Arrays.asList(assignables));
    }

    @Override
    public void assignTo(Target target) {
        assignables.forEach(assignable -> assignable.assignTo(target));
    }

    @Override
    public void resignFrom(Target target) {
        assignables.forEach(assignable -> assignable.resignFrom(target));
    }
}
