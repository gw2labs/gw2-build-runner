package de.ralfhergert.gw2.model.effect;

import de.ralfhergert.gw2.model.BuffOrConditionType;
import de.ralfhergert.gw2.modifier.CharacterAttributeModifier;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;

/**
 * Vigor increased endurance regeneration by 50%.
 */
public class VigorBuff extends DurationStackingBuffOrCondition {

    public VigorBuff(int numberOfStacks, Duration duration) {
        super(BuffOrConditionType.Vigor, numberOfStacks, duration, Duration.ofMinutes(3));
    }

    @Override
    protected Collection<CharacterAttributeModifier> createModifiersFor(int numberOfStacks) {
        return Collections.emptyList();
    }
}
