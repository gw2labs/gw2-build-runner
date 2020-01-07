package de.ralfhergert.generic.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * This group allows to collect multiple {@link Triggerable}s to trigger them all together.
 */
public class TriggerableGroup implements Triggerable {

    private final List<Triggerable> triggerables = new ArrayList<>();

    public TriggerableGroup(Triggerable... triggerables) {
        this(Arrays.asList(triggerables));
    }

    public TriggerableGroup(Collection<Triggerable> triggerables) {
        this.triggerables.addAll(triggerables);
    }

    @Override
    public void trigger() {
        triggerables.forEach(Triggerable::trigger);
    }
}
