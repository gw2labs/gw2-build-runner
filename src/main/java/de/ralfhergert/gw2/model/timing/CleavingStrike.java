package de.ralfhergert.gw2.model.timing;

import de.ralfhergert.gw2.model.Timing;
import de.ralfhergert.gw2.model.effector.TargetedEffector;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A cleaving strike covers a cone shaped area in front of the character
 * and can hit multiple targets in that area.
 */
public class CleavingStrike extends Timing {

    public int openingAngle;
    public int range;
    public int numberOfTargets;
    public List<TargetedEffector> effectors;

    public CleavingStrike(int openingAngle, int range, int numberOfTargets, TargetedEffector... effectors) {
        super(Duration.ZERO);
        this.openingAngle = openingAngle;
        this.range = range;
        this.numberOfTargets = numberOfTargets;
        this.effectors = new ArrayList<>(Arrays.asList(effectors));
    }
}
