package de.ralfhergert.gw2.model;

import de.ralfhergert.gw2.model.build.v1.BuildId;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the runes in {@link Rune}.
 */
public class RuneTest {

    @Test
    public void testFireworksRune6() {
        Gw2Character gw2Character = new BuildId().createFrom("GW2L1-p:Thief-e:r:Fireworks");
        Assert.assertNotNull("character should not be null", gw2Character);
        Assert.assertEquals("character should have no buffs", 0, gw2Character.getBuffOrConditions().count());

        gw2Character.setInCombat(true);
        Assert.assertEquals("character should have buffs", 3, gw2Character.getBuffOrConditions().count());
    }
}
