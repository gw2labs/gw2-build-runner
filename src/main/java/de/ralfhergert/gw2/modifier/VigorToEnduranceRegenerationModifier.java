package de.ralfhergert.gw2.modifier;

import de.ralfhergert.generic.value.ValueChangedHandler;
import de.ralfhergert.generic.value.ValueModifier;
import de.ralfhergert.gw2.model.BuffOrConditionType;
import de.ralfhergert.gw2.model.CharacterAttribute;
import de.ralfhergert.gw2.model.Gw2Character;
import de.ralfhergert.gw2.model.StackingBuffOrCondition;

/**
 * The boon Vigor stacks in duration. In order to apply the bonus of "plus by 50%" to
 * endurance regeneration only once, this global modifier is used, listening to any
 * Vigor boon being applied or removed.
 */
public class VigorToEnduranceRegenerationModifier extends ValueModifier<Number,Gw2Character> {

    private final ValueChangedHandler<StackingBuffOrCondition[],Gw2Character> listener = value -> check();

    private Gw2Character gw2Character = null;
    private boolean hasVigor = false;

    public VigorToEnduranceRegenerationModifier(Object source) {
        super(source);
    }

    @Override
    public void assignTo(Gw2Character context) {
        this.gw2Character = context;
        this.hasVigor = context.hasBuffOrCondition(BuffOrConditionType.Vigor);
        // register a listener
        context.getAttribute(CharacterAttribute.EffectingBuffOrConditions).addChangedHandler(listener);
        // attach as a modifier
        context.getAttribute(CharacterAttribute.EnduranceRegeneration).addModifier(this);
    }

    @Override
    public void resignFrom(Gw2Character context) {
        this.gw2Character = context;
        // unregister the listener
        context.getAttribute(CharacterAttribute.EffectingBuffOrConditions).removeChangedHandler(listener);
        // detach as a modifier
        context.getAttribute(CharacterAttribute.EnduranceRegeneration).removeModifier(this);
    }

    private void check() {
        if (gw2Character == null) {
            return;
        }
        final boolean nowHasVigor = gw2Character.hasBuffOrCondition(BuffOrConditionType.Vigor);
        if (hasVigor != nowHasVigor) {
            hasVigor = nowHasVigor;
            promoteModification();
        }
    }

    @Override
    public Number modify(Number value, Gw2Character context) {
        final double scale = hasVigor ? 1.5 : 1;
        return value.doubleValue() * scale;
    }
}
