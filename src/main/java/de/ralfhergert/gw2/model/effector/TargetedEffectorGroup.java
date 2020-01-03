package de.ralfhergert.gw2.model.effector;

import de.ralfhergert.gw2.model.Gw2Character;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TargetedEffectorGroup implements TargetedEffector {

    private final List<TargetedEffector> effectors = new ArrayList<>();

    public TargetedEffectorGroup(TargetedEffector... effectors) {
        this(Arrays.asList(effectors));
    }

    public TargetedEffectorGroup(Collection<TargetedEffector> effectors) {
        this.effectors.addAll(effectors);
    }

    public void perform(Gw2Character source, Gw2Character target, LocalTime time) {
        effectors.forEach(e -> e.perform(source, target, time));
    }
}
