package de.ralfhergert.gw2.model.effect;

import de.ralfhergert.generic.number.IncreaseByDouble;
import de.ralfhergert.gw2.model.*;
import de.ralfhergert.gw2.modifier.CharacterAttributeModifier;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Tests for {@link MightBuff}
 */
public class MightBuffTest {

    @Test
    public void testApplianceAndRemovalOfMightBuff() {
        Gw2Character gw2Character = new Gw2Character(Profession.Guardian);
        Assert.assertEquals("character time", LocalTime.of(0, 0, 0, 0), gw2Character.getAttributeValue(CharacterAttribute.CharacterAge, LocalTime.class));
        Assert.assertEquals("number of buffs", 0, gw2Character.getBuffOrConditions().count());
        Assert.assertEquals("power should be", 1000, gw2Character.getAttributeValue(CharacterAttribute.Power, Integer.class).intValue());
        Assert.assertEquals("condition damage should be", 0, gw2Character.getAttributeValue(CharacterAttribute.ConditionDamage, Integer.class).intValue());

        new MightBuff(3, Duration.ofSeconds(5), gw2Character).assignTo(gw2Character);
        Assert.assertEquals("number of buffs", 1, gw2Character.getBuffOrConditions().count());
        Assert.assertEquals("power should be", 1090, gw2Character.getAttributeValue(CharacterAttribute.Power, Integer.class).intValue());
        Assert.assertEquals("condition damage should be", 90, gw2Character.getAttributeValue(CharacterAttribute.ConditionDamage, Integer.class).intValue());

        gw2Character.advance(Duration.ofMillis(4900));
        Assert.assertEquals("character time", LocalTime.of(0, 0, 4, 900_000_000), gw2Character.getAttributeValue(CharacterAttribute.CharacterAge, LocalTime.class));
        Assert.assertEquals("number of buffs", 1, gw2Character.getBuffOrConditions().count());
        Assert.assertEquals("power should be", 1090, gw2Character.getAttributeValue(CharacterAttribute.Power, Integer.class).intValue());
        Assert.assertEquals("condition damage should be", 90, gw2Character.getAttributeValue(CharacterAttribute.ConditionDamage, Integer.class).intValue());

        gw2Character.advance(Duration.ofMillis(200));
        Assert.assertEquals("character time", LocalTime.of(0, 0, 5, 100_000_000), gw2Character.getAttributeValue(CharacterAttribute.CharacterAge, LocalTime.class));
        Assert.assertEquals("number of buffs", 0, gw2Character.getBuffOrConditions().count());
        Assert.assertEquals("power should be", 1000, gw2Character.getAttributeValue(CharacterAttribute.Power, Integer.class).intValue());
        Assert.assertEquals("condition damage should be", 0, gw2Character.getAttributeValue(CharacterAttribute.ConditionDamage, Integer.class).intValue());
    }

    @Test
    public void testNotMoreThan25StacksOfMightCanBeApplied01() {
        Gw2Character gw2Character = new Gw2Character(Profession.Guardian);
        Assert.assertEquals("character time", LocalTime.of(0, 0, 0, 0), gw2Character.getAttributeValue(CharacterAttribute.CharacterAge, LocalTime.class));
        Assert.assertEquals("number of buffs", 0, gw2Character.getBuffOrConditions().count());
        Assert.assertEquals("power should be", 1000, gw2Character.getAttributeValue(CharacterAttribute.Power, Integer.class).intValue());
        Assert.assertEquals("condition damage should be", 0, gw2Character.getAttributeValue(CharacterAttribute.ConditionDamage, Integer.class).intValue());

        // apply a single buff with 25 stacks
        new MightBuff(25, Duration.ofSeconds(1), gw2Character).assignTo(gw2Character);
        Assert.assertEquals("number of buffs", 1, gw2Character.getBuffOrConditions().count());
        Assert.assertEquals("number of might stacks", 25, gw2Character.getBuffOrConditions(BuffOrConditionType.Might)
            .map(StackingBuffOrCondition::getAppliedNumberOfStacks)
            .reduce(0, Integer::sum)
            .intValue());
        Assert.assertEquals("power should be", 1750, gw2Character.getAttributeValue(CharacterAttribute.Power, Integer.class).intValue());
        Assert.assertEquals("condition damage should be", 750, gw2Character.getAttributeValue(CharacterAttribute.ConditionDamage, Integer.class).intValue());

        // try applying one stack more - verify that it was nullified
        new MightBuff(1, Duration.ofSeconds(1), gw2Character).assignTo(gw2Character);
        Assert.assertEquals("number of buffs", 1, gw2Character.getBuffOrConditions().count());
        Assert.assertEquals("number of might stacks", 25, gw2Character.getBuffOrConditions(BuffOrConditionType.Might)
            .map(StackingBuffOrCondition::getAppliedNumberOfStacks)
            .reduce(0, Integer::sum)
            .intValue());
        Assert.assertEquals("power should be", 1750, gw2Character.getAttributeValue(CharacterAttribute.Power, Integer.class).intValue());
        Assert.assertEquals("condition damage should be", 750, gw2Character.getAttributeValue(CharacterAttribute.ConditionDamage, Integer.class).intValue());
    }

    @Test
    public void testNotMoreThan25StacksOfMightCanBeApplied02() {
        Gw2Character gw2Character = new Gw2Character(Profession.Guardian);
        Assert.assertEquals("character time", LocalTime.of(0, 0, 0, 0), gw2Character.getAttributeValue(CharacterAttribute.CharacterAge, LocalTime.class));
        Assert.assertEquals("number of buffs", 0, gw2Character.getBuffOrConditions().count());
        Assert.assertEquals("power should be", 1000, gw2Character.getAttributeValue(CharacterAttribute.Power, Integer.class).intValue());
        Assert.assertEquals("condition damage should be", 0, gw2Character.getAttributeValue(CharacterAttribute.ConditionDamage, Integer.class).intValue());

        // apply a single buff with 20 stacks
        new MightBuff(20, Duration.ofSeconds(1), gw2Character).assignTo(gw2Character);
        Assert.assertEquals("number of buffs", 1, gw2Character.getBuffOrConditions().count());
        Assert.assertEquals("number of might stacks", 20, gw2Character.getBuffOrConditions(BuffOrConditionType.Might)
            .map(StackingBuffOrCondition::getAppliedNumberOfStacks)
            .reduce(0, Integer::sum)
            .intValue());
        Assert.assertEquals("power should be", 1600, gw2Character.getAttributeValue(CharacterAttribute.Power, Integer.class).intValue());
        Assert.assertEquals("condition damage should be", 600, gw2Character.getAttributeValue(CharacterAttribute.ConditionDamage, Integer.class).intValue());

        // apply one more buff with 10 stack - only 5 of them should be applied.
        new MightBuff(10, Duration.ofSeconds(1), gw2Character).assignTo(gw2Character);
        Assert.assertEquals("number of buffs", 2, gw2Character.getBuffOrConditions().count());
        Assert.assertEquals("number of might stacks", 25, gw2Character.getBuffOrConditions(BuffOrConditionType.Might)
            .map(StackingBuffOrCondition::getAppliedNumberOfStacks)
            .reduce(0, Integer::sum)
            .intValue());
        Assert.assertEquals("power should be", 1750, gw2Character.getAttributeValue(CharacterAttribute.Power, Integer.class).intValue());
        Assert.assertEquals("condition damage should be", 750, gw2Character.getAttributeValue(CharacterAttribute.ConditionDamage, Integer.class).intValue());
    }

    @Test
    public void testMightIsEffectedByBoonDuration01() {
        Gw2Character gw2Character = new Gw2Character(Profession.Guardian);
        Assert.assertEquals("character time", LocalTime.of(0, 0, 0, 0), gw2Character.getAttributeValue(CharacterAttribute.CharacterAge, LocalTime.class));
        Assert.assertEquals("number of buffs", 0, gw2Character.getBuffOrConditions().count());
        Assert.assertEquals("boon duration should be", 0, gw2Character.getAttributeValue(CharacterAttribute.BoonDuration, Double.class), 0.0001);

        // apply a single stack of might lasting 4 seconds
        new MightBuff(1, Duration.ofSeconds(4), gw2Character).assignTo(gw2Character);
        Assert.assertEquals("number of buffs", 1, gw2Character.getBuffOrConditions().count());

        /* after 2 sec alter the characters boon duration to 25%
         * that should lead the might buff with its remaining 2s to last 2.5s instead. */
        gw2Character.advance(Duration.ofSeconds(2));
        Assert.assertEquals("character time", LocalTime.of(0, 0, 2, 0), gw2Character.getAttributeValue(CharacterAttribute.CharacterAge, LocalTime.class));
        new CharacterAttributeModifier(this, CharacterAttribute.BoonDuration, new IncreaseByDouble(0.25)).assignTo(gw2Character);

        gw2Character.advance(Duration.ofSeconds(2, 450_000_000));
        Assert.assertEquals("character time", LocalTime.of(0, 0, 4, 450_000_000), gw2Character.getAttributeValue(CharacterAttribute.CharacterAge, LocalTime.class));
        Assert.assertEquals("might buff should still be applied", 1, gw2Character.getBuffOrConditions().count());

        gw2Character.advance(Duration.ofSeconds(0, 100_000_000));
        Assert.assertEquals("character time", LocalTime.of(0, 0, 4, 550_000_000), gw2Character.getAttributeValue(CharacterAttribute.CharacterAge, LocalTime.class));
        Assert.assertEquals("might buff should no longer be applied", 0, gw2Character.getBuffOrConditions().count());
    }
}
