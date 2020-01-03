package de.ralfhergert.gw2.modifier;

import de.ralfhergert.generic.value.ValueChangedHandler;
import de.ralfhergert.generic.value.ValueModifier;
import de.ralfhergert.gw2.model.BuffOrConditionType;
import de.ralfhergert.gw2.model.CharacterAttribute;
import de.ralfhergert.gw2.model.Gw2Character;
import de.ralfhergert.gw2.model.StackingBuffOrCondition;

/**
 * The boon Fury stacks in duration. In order to apply the bonus of +20% critical chance
 * only once, this global modifier is used, listening to any Fury boon being applied or
 * removed.
 */
public class FuryToCriticalChanceModifier extends ValueModifier<Number,Gw2Character> {

    private final ValueChangedHandler<StackingBuffOrCondition[],Gw2Character> listener = value -> check();

    private Gw2Character gw2Character = null;
    private boolean hasFury = false;

    public FuryToCriticalChanceModifier(Object source) {
        super(source);
    }

    @Override
    public void assignTo(Gw2Character context) {
        this.gw2Character = context;
        this.hasFury = context.hasBuffOrCondition(BuffOrConditionType.Fury);
        // register a listener
        context.getAttribute(CharacterAttribute.EffectingBuffOrConditions).addChangedHandler(listener);
        // attach as a modifier
        context.getAttribute(CharacterAttribute.CriticalChance).addModifier(this);
    }

    @Override
    public void resignFrom(Gw2Character context) {
        this.gw2Character = context;
        // unregister the listener
        context.getAttribute(CharacterAttribute.EffectingBuffOrConditions).removeChangedHandler(listener);
        // detach as a modifier
        context.getAttribute(CharacterAttribute.CriticalChance).removeModifier(this);
    }

    private void check() {
        if (gw2Character == null) {
            return;
        }
        final boolean nowHasFury = gw2Character.hasBuffOrCondition(BuffOrConditionType.Fury);
        if (hasFury != nowHasFury) {
            hasFury = nowHasFury;
            promoteModification();
        }
    }

    @Override
    public Number modify(Number value, Gw2Character context) {
        final double bonus = hasFury ? 0.2 : 0;
        return value.doubleValue() + bonus;
    }
}
