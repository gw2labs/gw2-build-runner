package de.ralfhergert.gw2.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * This test ensures that {@link Gw2Character} is working correctly.
 */
public class Gw2CharacterTest {

    @Test
    public void testHealthOnNakedWarrior() {
        Assert.assertEquals("Health of a naked warrior", 19212, new Gw2Character(Profession.Warrior).getAttributeValue(CharacterAttribute.Health));
    }

    @Test
    public void testArmorOnNakedNecromancer() {
        Assert.assertEquals("Armor of a naked necromancer", 1000, new Gw2Character(Profession.Necromancer).getAttributeValue(CharacterAttribute.Armor));
    }

    @Test
    public void testCriticalChanceOnNakedThief() {
        Assert.assertEquals("CriticalChance of a naked thief", 0.05, new Gw2Character(Profession.Thief).getAttributeValue(CharacterAttribute.CriticalChance));
    }
}
