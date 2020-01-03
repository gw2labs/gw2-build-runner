package de.ralfhergert.gw2.model.effector;

import de.ralfhergert.gw2.model.Gw2Character;

import java.time.LocalTime;

public interface TargetedEffector {

    void perform(Gw2Character source, Gw2Character target, LocalTime time);
}
