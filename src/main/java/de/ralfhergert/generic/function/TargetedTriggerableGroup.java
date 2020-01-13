package de.ralfhergert.generic.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * This group allows to collect multiple {@link Triggerable}s to trigger them all together.
 */
public class TargetedTriggerableGroup<Source,Target> implements TargetedTriggerable<Source,Target> {

    private final List<TargetedTriggerable<Source,Target>> triggerables = new ArrayList<>();

    public TargetedTriggerableGroup(TargetedTriggerable<Source,Target>... triggerables) {
        this(Arrays.asList(triggerables));
    }

    public TargetedTriggerableGroup(Collection<TargetedTriggerable<Source,Target>> triggerables) {
        this.triggerables.addAll(triggerables);
    }

    @Override
    public void trigger(Source source, Target target) {
        triggerables.forEach(triggerable -> triggerable.trigger(source, target));
    }
}
