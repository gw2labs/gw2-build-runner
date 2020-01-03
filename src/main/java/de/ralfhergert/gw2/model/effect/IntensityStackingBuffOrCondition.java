package de.ralfhergert.gw2.model.effect;

import de.ralfhergert.gw2.model.StackingBuffOrCondition;
import de.ralfhergert.gw2.model.BuffOrConditionType;
import de.ralfhergert.gw2.modifier.CharacterAttributeModifier;

import java.time.Duration;
import java.util.Collection;

/**
 * Stackable buffs or conditions increase the number of stack of a certain type.
 * If the maximum threshold is met for the buff or condition type no further stacks
 * can be applied. For that reason the numberOfStack are separated into "wanted" and
 * "applied" number of stacks.
 *
 * @see <a href="https://wiki.guildwars2.com/wiki/Effect_stacking">Event Stacking</a>
 */
public abstract class IntensityStackingBuffOrCondition extends StackingBuffOrCondition {

    public IntensityStackingBuffOrCondition(BuffOrConditionType type, int wantedNumberOfStacks, Duration duration) {
        super(type, wantedNumberOfStacks, duration);
    }

    protected abstract Collection<CharacterAttributeModifier> createModifiersFor(int numberOfStacks);
}
