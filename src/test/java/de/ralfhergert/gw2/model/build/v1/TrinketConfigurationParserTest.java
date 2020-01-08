package de.ralfhergert.gw2.model.build.v1;

import de.ralfhergert.gw2.model.EquipmentSlot;
import de.ralfhergert.gw2.model.Prefix;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

/**
 * Tests for {@link TrinketConfiguration}
 */
public class TrinketConfigurationParserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testParsingNull() {
        thrown.expect(IllegalArgumentException.class);
        new TrinketConfigurationParser().apply(null);
    }

    @Test
    public void testParsingEmpty() {
        thrown.expect(IllegalArgumentException.class);
        new TrinketConfigurationParser().apply("");
    }

    @Test
    public void testAgaintsUnknownPrefix() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Could not find the appropriate prefix for 'FooBar'");
        new TrinketConfigurationParser().apply("FooBar");
    }

    @Test
    public void testShortFormForAllBerserker() {
        TrinketConfiguration config = new TrinketConfigurationParser().apply("Berserker");
        Assert.assertNotNull("config should not be null", config);
        Assert.assertEquals("prefix should be", Prefix.Berserker, config.getPrefix());
        Assert.assertEquals("number of slots", 6, config.getSlots().size());
    }

    @Test
    public void testStarFormForAllBerserker() {
        TrinketConfiguration config = new TrinketConfigurationParser().apply("*=Berserk");
        Assert.assertNotNull("config should not be null", config);
        Assert.assertEquals("prefix should be", Prefix.Berserker, config.getPrefix());
        Assert.assertEquals("number of slots", 6, config.getSlots().size());
    }

    @Test
    public void testAllFormForAllBerserker() {
        TrinketConfiguration config = new TrinketConfigurationParser().apply("=Berser");
        Assert.assertNotNull("config should not be null", config);
        Assert.assertEquals("prefix should be", Prefix.Berserker, config.getPrefix());
        Assert.assertEquals("number of slots", 6, config.getSlots().size());
    }

    @Test
    public void testNeckOnlyAsMinstrel() {
        TrinketConfiguration config = new TrinketConfigurationParser().apply("Ne=Minstre");
        Assert.assertNotNull("config should not be null", config);
        Assert.assertEquals("prefix should be", Prefix.Minstrel, config.getPrefix());
        Assert.assertEquals("number of slots", 1, config.getSlots().size());
        Assert.assertEquals("slot should be", EquipmentSlot.Neck, config.getSlots().get(0));
    }

    @Test
    public void testBothFingersAsMinstrel() {
        TrinketConfiguration config = new TrinketConfigurationParser().apply("FL,FR=Minstre");
        Assert.assertNotNull("config should not be null", config);
        Assert.assertEquals("prefix should be", Prefix.Minstrel, config.getPrefix());
        Assert.assertEquals("slots should be",
            Arrays.asList(EquipmentSlot.FingerLeft, EquipmentSlot.FingerRight),
            config.getSlots());
    }
}
