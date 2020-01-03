package de.ralfhergert.gw2.model.effector;

import de.ralfhergert.gw2.model.StackingBuffOrCondition;
import de.ralfhergert.gw2.model.Gw2Character;

import java.time.LocalTime;
import java.util.function.Supplier;

public class ApplyBuffOrConditionEffector implements TargetedEffector {

    private Gw2Character sourceCharacter;
    private Gw2Character targetCharacter;
    private Supplier<StackingBuffOrCondition> buffOrConditionSupplier;


    @Override
    public void perform(Gw2Character source, Gw2Character target, LocalTime time) {

    }
}
