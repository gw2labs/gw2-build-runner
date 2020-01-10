package de.ralfhergert.gw2.model.build.v1;

import de.ralfhergert.gw2.model.Rune;
import de.ralfhergert.gw2.model.RuneSlot;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;

/**
 * Tests for {@link RuneConfigurationParser}.
 */
public class RuneConfigurationParserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testAgainstNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("configuration text must not be null");
        new RuneConfigurationParser().apply(null);
    }

    @Test
    public void testAgainstEmpty() {
        thrown.expect(IllegalArgumentException.class);
        new RuneConfigurationParser().apply("");
    }

    @Test
    public void testAgainstUnknown() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Could not find the appropriate rune for 'Foobar'");
        new RuneConfigurationParser().apply("Foobar");
    }

    @Test
    public void testHeadWithFireworksRune() {
        RuneConfiguration config = new RuneConfigurationParser().apply("He=Firew");
        Assert.assertNotNull("config should not be null", config);
        Assert.assertEquals("slot should be", Collections.singletonList(RuneSlot.Head), config.getRuneSlots());
        Assert.assertEquals("rune should be", Rune.Fireworks, config.getRune());
    }

    @Test
    public void testAllWithFireworksRune() {
        RuneConfiguration config = new RuneConfigurationParser().apply("=Firework");
        Assert.assertNotNull("config should not be null", config);
        Assert.assertEquals("slot should be",
            Arrays.asList(RuneSlot.Head, RuneSlot.Shoulders, RuneSlot.Chest, RuneSlot.Hands, RuneSlot.Legs, RuneSlot.Feet),
            config.getRuneSlots());
        Assert.assertEquals("rune should be", Rune.Fireworks, config.getRune());
    }

    @Test
    public void testStarWithFireworksRune() {
        RuneConfiguration config = new RuneConfigurationParser().apply("*=Fireworks");
        Assert.assertNotNull("config should not be null", config);
        Assert.assertEquals("slot should be",
            Arrays.asList(RuneSlot.Head, RuneSlot.Shoulders, RuneSlot.Chest, RuneSlot.Hands, RuneSlot.Legs, RuneSlot.Feet),
            config.getRuneSlots());
        Assert.assertEquals("rune should be", Rune.Fireworks, config.getRune());
    }
}
