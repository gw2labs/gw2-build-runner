package de.ralfhergert.gw2.ability;

import de.ralfhergert.generic.function.TargetedTriggerable;
import de.ralfhergert.generic.function.Triggerable;
import de.ralfhergert.gw2.model.Gw2Character;

import java.time.LocalTime;

public class GrantNearbyAllies implements Triggerable<Gw2Character> {

    private final int numberOfTargets;
    private final int range;
    private final boolean includeSourcePlayer;
    private final TargetedTriggerable<Gw2Character,Gw2Character> triggerable;

    private Gw2Character gw2Character = null;
    private LocalTime lastTriggered = null;

    public GrantNearbyAllies(int numberOfTargets, int range, boolean includeSourcePlayer, TargetedTriggerable<Gw2Character,Gw2Character> triggerable) {
        this.numberOfTargets = numberOfTargets;
        this.range = range;
        this.includeSourcePlayer = includeSourcePlayer;
        this.triggerable = triggerable;
    }

    public void trigger(Gw2Character source) {

    }
}
