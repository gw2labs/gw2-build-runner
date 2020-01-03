package de.ralfhergert.gw2.model;

import de.ralfhergert.gw2.modifier.CharacterAttributeModifier;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Optional;

/**
 * This class ensure the {@link Prefix} are correct.
 */
@RunWith(Parameterized.class)
public class PrefixTest {

    private final Prefix prefix;

    public PrefixTest(Prefix prefix) {
        this.prefix = prefix;
    }

    @Parameterized.Parameters
    public static Prefix[] getPrefixes() {
        return Prefix.values();
    }

    @Test
    public void ensureTheSumOfModifiersIsCorrect() {
        Assert.assertNotNull("prefix should not be null", prefix);

        final int expectedSum;
        switch (prefix.getModifiers().size()) {
            case 3: expectedSum = 343; break;
            case 4: expectedSum = 376; break;
            case 7: expectedSum = 469; break;
            default: Assert.fail("unexpected number of modifiers on prefix '" + prefix.name() + "'"); return;
        }

        final int sum = prefix.getModifiers().stream()
            .map(CharacterAttributeModifier::getIncrease)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(Number::intValue)
            .reduce(0, Integer::sum);

        Assert.assertEquals("sum of prefix '" + prefix.name() + "' is not correct", expectedSum, sum);
    }
}
