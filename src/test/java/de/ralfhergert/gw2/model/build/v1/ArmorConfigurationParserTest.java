package de.ralfhergert.gw2.model.build.v1;

import de.ralfhergert.gw2.model.ArmorSlot;
import de.ralfhergert.gw2.model.Prefix;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;

/**
 * Tests for {@link ArmorConfigurationParser}.
 */
public class ArmorConfigurationParserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testAgainstNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("configuration must not be null");
        new ArmorConfigurationParser().apply(null);
    }

    @Test
    public void testAgainstEmpty() {
        thrown.expect(IllegalArgumentException.class);
        new ArmorConfigurationParser().apply("");
    }

    @Test
    public void testAgainstUnknown() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Could not find the appropriate prefix for 'Foobar'");
        new ArmorConfigurationParser().apply("Foobar");
    }

    @Test
    public void testFullZojja() {
        ArmorConfiguration config = new ArmorConfigurationParser().apply("Zojja");
        Assert.assertNotNull("config should not be null", config);
        Assert.assertEquals("slots should be",
            Arrays.asList(ArmorSlot.Head, ArmorSlot.Shoulders, ArmorSlot.Chest, ArmorSlot.Hands, ArmorSlot.Legs, ArmorSlot.Feet),
            config.getArmorSlots());
        Assert.assertEquals("prefix should be", Prefix.Berserker, config.getArmorPrefix());
    }

    @Test
    public void testAllKnight() {
        ArmorConfiguration config = new ArmorConfigurationParser().apply("=Beigarth");
        Assert.assertNotNull("config should not be null", config);
        Assert.assertEquals("slots should be",
            Arrays.asList(ArmorSlot.Head, ArmorSlot.Shoulders, ArmorSlot.Chest, ArmorSlot.Hands, ArmorSlot.Legs, ArmorSlot.Feet),
            config.getArmorSlots());
        Assert.assertEquals("prefix should be", Prefix.Knight, config.getArmorPrefix());
    }

    @Test
    public void testStarKnight() {
        ArmorConfiguration config = new ArmorConfigurationParser().apply("*=Sentinel");
        Assert.assertNotNull("config should not be null", config);
        Assert.assertEquals("slots should be",
            Arrays.asList(ArmorSlot.Head, ArmorSlot.Shoulders, ArmorSlot.Chest, ArmorSlot.Hands, ArmorSlot.Legs, ArmorSlot.Feet),
            config.getArmorSlots());
        Assert.assertEquals("prefix should be", Prefix.Sentinel, config.getArmorPrefix());
    }

    @Test
    public void testNameSearch01() {
        ArmorConfiguration config = new ArmorConfigurationParser().apply("Head=n'Pahua");
        Assert.assertNotNull("config should not be null", config);
        Assert.assertEquals("slots should be", Collections.singletonList(ArmorSlot.Head), config.getArmorSlots());
        Assert.assertEquals("prefix should be", Prefix.Trailblazer, config.getArmorPrefix());
    }

    @Test
    public void testNameSearch02() {
        ArmorConfiguration config = new ArmorConfigurationParser().apply("Head=n'Wei Qi");
        Assert.assertNotNull("config should not be null", config);
        Assert.assertEquals("slots should be", Collections.singletonList(ArmorSlot.Head), config.getArmorSlots());
        Assert.assertEquals("prefix should be", Prefix.Sentinel, config.getArmorPrefix());
    }

    @Test
    public void testPrefixSearch() {
        ArmorConfiguration config = new ArmorConfigurationParser().apply("Head=p'Trail");
        Assert.assertNotNull("config should not be null", config);
        Assert.assertEquals("slots should be", Collections.singletonList(ArmorSlot.Head), config.getArmorSlots());
        Assert.assertEquals("prefix should be", Prefix.Trailblazer, config.getArmorPrefix());
    }
}
