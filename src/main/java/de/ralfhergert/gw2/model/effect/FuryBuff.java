package de.ralfhergert.gw2.model.effect;

import de.ralfhergert.gw2.model.BuffOrConditionType;
import de.ralfhergert.gw2.modifier.CharacterAttributeModifier;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;

/**
 * Fury grants +20% to Critical Chance.
 */
public class FuryBuff extends DurationStackingBuffOrCondition {

    public FuryBuff(int numberOfStacks, Duration duration) {
        super(BuffOrConditionType.Fury, numberOfStacks, duration, Duration.ofMinutes(3));
    }

    @Override
    protected Collection<CharacterAttributeModifier> createModifiersFor(int numberOfStacks) {
        return Collections.emptyList();
    }
}
