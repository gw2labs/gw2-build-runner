package de.ralfhergert.gw2.model.effect;

import de.ralfhergert.generic.number.IncreaseByInt;
import de.ralfhergert.gw2.model.BuffOrConditionType;
import de.ralfhergert.gw2.model.CharacterAttribute;
import de.ralfhergert.gw2.model.Gw2Character;
import de.ralfhergert.gw2.modifier.CharacterAttributeModifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

/**
 * For a level 80 character each stack of might grants
 * +30 Power and +30 Condition Damage.
 */
public class MightBuff extends IntensityStackingBuffOrCondition {

    public MightBuff(int numberOfStacks, Duration duration, Gw2Character sourceCharacter) {
        super(BuffOrConditionType.Might, numberOfStacks, duration, sourceCharacter);
    }

    @Override
    protected Collection<CharacterAttributeModifier> createModifiersFor(final int numberOfStacks) {
        return Arrays.asList(
            new CharacterAttributeModifier(this, CharacterAttribute.Power, new IncreaseByInt(30 * numberOfStacks)),
            new CharacterAttributeModifier(this, CharacterAttribute.ConditionDamage, new IncreaseByInt(30 * numberOfStacks))
        );
    }
}
