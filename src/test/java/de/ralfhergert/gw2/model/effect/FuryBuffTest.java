package de.ralfhergert.gw2.model.effect;

import de.ralfhergert.gw2.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Tests for {@link FuryBuff}
 */
public class FuryBuffTest {

    @Test
    public void testApplianceAndRemovalOfFuryBuff() {
        Gw2Character gw2Character = new Gw2Character(Profession.Thief);
        Assert.assertEquals("character time", LocalTime.of(0, 0, 0, 0), gw2Character.getAttributeValue(CharacterAttribute.CharacterAge, LocalTime.class));
        Assert.assertEquals("number of buffs", 0, gw2Character.getBuffOrConditions().count());
        Assert.assertEquals("critical chance should be", 0.05, gw2Character.getAttributeValue(CharacterAttribute.CriticalChance, Double.class), 0.0001);

        new FuryBuff(1, Duration.ofSeconds(5), gw2Character).assignTo(gw2Character);
        Assert.assertEquals("number of buffs", 1, gw2Character.getBuffOrConditions().count());
        Assert.assertEquals("critical chance should be", 0.25, gw2Character.getAttributeValue(CharacterAttribute.CriticalChance, Double.class), 0.0001);

        gw2Character.advance(Duration.ofMillis(4900));
        Assert.assertEquals("character time", LocalTime.of(0, 0, 4, 900_000_000), gw2Character.getAttributeValue(CharacterAttribute.CharacterAge, LocalTime.class));
        Assert.assertEquals("number of buffs", 1, gw2Character.getBuffOrConditions().count());
        Assert.assertEquals("critical chance should be", 0.25, gw2Character.getAttributeValue(CharacterAttribute.CriticalChance, Double.class), 0.0001);

        gw2Character.advance(Duration.ofMillis(200));
        Assert.assertEquals("character time", LocalTime.of(0, 0, 5, 100_000_000), gw2Character.getAttributeValue(CharacterAttribute.CharacterAge, LocalTime.class));
        Assert.assertEquals("number of buffs", 0, gw2Character.getBuffOrConditions().count());
        Assert.assertEquals("critical chance should be", 0.05, gw2Character.getAttributeValue(CharacterAttribute.CriticalChance, Double.class), 0.0001);
    }

    @Test
    public void testNotMoreThan5StacksOfFuryCanBeApplied01() {
        Gw2Character gw2Character = new Gw2Character(Profession.Thief);
        Assert.assertEquals("character time", LocalTime.of(0, 0, 0, 0), gw2Character.getAttributeValue(CharacterAttribute.CharacterAge, LocalTime.class));
        Assert.assertEquals("number of buffs", 0, gw2Character.getBuffOrConditions().count());
        Assert.assertEquals("critical chance should be", 0.05, gw2Character.getAttributeValue(CharacterAttribute.CriticalChance, Double.class), 0.0001);

        // apply a single buff with 9 stacks
        new FuryBuff(9, Duration.ofSeconds(5), gw2Character).assignTo(gw2Character);
        Assert.assertEquals("number of buffs", 1, gw2Character.getBuffOrConditions().count());
        Assert.assertEquals("number of fury stacks", 9, gw2Character.getBuffOrConditions(BuffOrConditionType.Fury)
            .map(StackingBuffOrCondition::getAppliedNumberOfStacks)
            .reduce(0, Integer::sum)
            .intValue());
        Assert.assertEquals("critical chance should be", 0.25, gw2Character.getAttributeValue(CharacterAttribute.CriticalChance, Double.class), 0.0001);

        // try applying one more buff with 1 stack - it should be nullified.
        new FuryBuff(1, Duration.ofSeconds(5), gw2Character).assignTo(gw2Character);
        Assert.assertEquals("number of buffs", 1, gw2Character.getBuffOrConditions().count());
        Assert.assertEquals("number of fury stacks", 9, gw2Character.getBuffOrConditions(BuffOrConditionType.Fury)
            .map(StackingBuffOrCondition::getAppliedNumberOfStacks)
            .reduce(0, Integer::sum)
            .intValue());
        Assert.assertEquals("critical chance should be", 0.25, gw2Character.getAttributeValue(CharacterAttribute.CriticalChance, Double.class), 0.0001);
    }

    @Test
    public void testNotMoreThan5StacksOfFuryCanBeApplied02() {
        Gw2Character gw2Character = new Gw2Character(Profession.Thief);
        Assert.assertEquals("character time", LocalTime.of(0, 0, 0, 0), gw2Character.getAttributeValue(CharacterAttribute.CharacterAge, LocalTime.class));
        Assert.assertEquals("number of buffs", 0, gw2Character.getBuffOrConditions().count());
        Assert.assertEquals("critical chance should be", 0.05, gw2Character.getAttributeValue(CharacterAttribute.CriticalChance, Double.class), 0.0001);

        // apply a single buff with 5 stacks
        new FuryBuff(5, Duration.ofSeconds(5), gw2Character).assignTo(gw2Character);
        Assert.assertEquals("number of buffs", 1, gw2Character.getBuffOrConditions().count());
        Assert.assertEquals("number of fury stacks", 5, gw2Character.getBuffOrConditions(BuffOrConditionType.Fury)
            .map(StackingBuffOrCondition::getAppliedNumberOfStacks)
            .reduce(0, Integer::sum)
            .intValue());
        Assert.assertEquals("critical chance should be", 0.25, gw2Character.getAttributeValue(CharacterAttribute.CriticalChance, Double.class), 0.0001);

        // try applying one more buff with 5 stack - it should be partially applied.
        new FuryBuff(5, Duration.ofSeconds(5), gw2Character).assignTo(gw2Character);
        Assert.assertEquals("number of buffs", 2, gw2Character.getBuffOrConditions().count());
        Assert.assertEquals("number of fury stacks", 9, gw2Character.getBuffOrConditions(BuffOrConditionType.Fury)
            .map(StackingBuffOrCondition::getAppliedNumberOfStacks)
            .reduce(0, Integer::sum)
            .intValue());
        Assert.assertEquals("critical chance should be", 0.25, gw2Character.getAttributeValue(CharacterAttribute.CriticalChance, Double.class), 0.0001);
    }
}
