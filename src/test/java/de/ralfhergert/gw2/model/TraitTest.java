package de.ralfhergert.gw2.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This test ensure that the definitions in {@link Trait} are consistent.
 */
public class TraitTest {

    @Test
    public void ensureAllTraitIdsAreUnique() {
        Map<Integer,Trait> seenTraits = new HashMap<>();
        for (Trait trait : Trait.values()) {
            Trait other = seenTraits.put(trait.getId(), trait);
            if (other != null) {
                Assert.fail("two traits with the same id found: " + other + " and " + trait);
            }
        }
    }

    @Test
    public void ensureEachTierHasExactlyThreeMajorTraits() {
        for (Specialization specialization : Specialization.values()) {
            for (int t = 1; t <= 3; t++) {
                final int tier = t;
                Map<Integer,Trait> foundTraits = Stream.of(Trait.values())
                    .filter(trait -> trait.getSpecialization() == specialization)
                    .filter(trait -> trait.getTier() == tier)
                    .filter(trait -> trait.getSlot() == TraitSlot.Major)
                    .collect(Collectors.toMap(Trait::getOrder, Function.identity()));

                Assert.assertEquals("number of major traits in specialization " + specialization + " and tier " + tier, 3, foundTraits.size());
                Assert.assertNotNull("in specialization " + specialization + " and tier " + tier + " a order 0 trait should exist", foundTraits.get(0));
                Assert.assertNotNull("in specialization " + specialization + " and tier " + tier + " a order 1 trait should exist", foundTraits.get(1));
                Assert.assertNotNull("in specialization " + specialization + " and tier " + tier + " a order 2 trait should exist", foundTraits.get(2));
            }
        }
    }
}
