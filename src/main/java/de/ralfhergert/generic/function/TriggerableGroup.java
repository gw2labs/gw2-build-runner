package de.ralfhergert.generic.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * This group allows to collect multiple {@link Triggerable}s to trigger them all together.
 */
public class TriggerableGroup<Source> implements Triggerable<Source> {

    private final List<Triggerable<Source>> triggerables = new ArrayList<>();

    public TriggerableGroup(Triggerable<Source>... triggerables) {
        this(Arrays.asList(triggerables));
    }

    public TriggerableGroup(Collection<Triggerable<Source>> triggerables) {
        this.triggerables.addAll(triggerables);
    }

    @Override
    public void trigger(Source source) {
        triggerables.forEach(triggerable -> triggerable.trigger(source));
    }
}
