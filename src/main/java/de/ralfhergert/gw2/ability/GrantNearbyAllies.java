package de.ralfhergert.gw2.ability;

import de.ralfhergert.generic.function.TargetedTriggerable;
import de.ralfhergert.generic.function.Triggerable;
import de.ralfhergert.gw2.model.Gw2Character;
import de.ralfhergert.gw2.model.World;

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
        World world = source.getWorld();
        if (world == null) {
            return;
        }
        world.findAll(gw2c -> gw2c.getTeam() == source.getTeam())
            .filter(gw2c -> gw2c.getPosition().getPosition().sub(source.getPosition().getPosition()).length() <= range)
            // randomize order or sort by nearest first?
            .limit(numberOfTargets)
            .forEach(gw2c -> triggerable.trigger(source, gw2c));
    }
}
