package de.ralfhergert.gw2.ability;

import de.ralfhergert.generic.function.SupplierFor;
import de.ralfhergert.generic.function.Triggerable;
import de.ralfhergert.generic.value.Assignable;
import de.ralfhergert.gw2.model.Gw2Character;

public class ApplyAssignableToSelf implements Triggerable<Gw2Character> {

    private final SupplierFor<Gw2Character,Assignable<Gw2Character>> assignableSupplier;

    public ApplyAssignableToSelf(SupplierFor<Gw2Character,Assignable<Gw2Character>> assignableSupplier) {
        this.assignableSupplier = assignableSupplier;
    }

    @Override
    public void trigger(Gw2Character gw2Character) {
        assignableSupplier.get(gw2Character).assignTo(gw2Character);
    }
}
