package de.ralfhergert.gw2.ability;

import de.ralfhergert.generic.function.SupplierFor;
import de.ralfhergert.generic.function.TargetedTriggerable;
import de.ralfhergert.generic.value.Assignable;
import de.ralfhergert.gw2.model.Gw2Character;

/**
 * Applies the buff or condition created by {@link #assignableSupplier} to the given target.
 */
public class ApplyAssignableToTarget implements TargetedTriggerable<Gw2Character,Gw2Character> {

    private final SupplierFor<Gw2Character, Assignable<Gw2Character>> assignableSupplier;

    public ApplyAssignableToTarget(SupplierFor<Gw2Character,Assignable<Gw2Character>> assignableSupplier) {
        this.assignableSupplier = assignableSupplier;
    }

    @Override
    public void trigger(Gw2Character sourceCharacter, Gw2Character targetCharacter) {
        assignableSupplier.get(sourceCharacter).assignTo(targetCharacter);
    }
}
