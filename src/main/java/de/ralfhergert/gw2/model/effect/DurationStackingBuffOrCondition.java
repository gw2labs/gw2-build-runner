package de.ralfhergert.gw2.model.effect;

import de.ralfhergert.gw2.model.StackingBuffOrCondition;
import de.ralfhergert.gw2.model.BuffOrConditionType;
import de.ralfhergert.gw2.modifier.CharacterAttributeModifier;

import java.time.Duration;
import java.util.Collection;

public abstract class DurationStackingBuffOrCondition extends StackingBuffOrCondition {

    private final Duration durationCap;

    public DurationStackingBuffOrCondition(BuffOrConditionType type, int wantedNumberOfStacks, Duration duration, Duration durationCap) {
        super(type, wantedNumberOfStacks, duration);
        this.durationCap = durationCap;
    }

    protected abstract Collection<CharacterAttributeModifier> createModifiersFor(int numberOfStacks);
}
