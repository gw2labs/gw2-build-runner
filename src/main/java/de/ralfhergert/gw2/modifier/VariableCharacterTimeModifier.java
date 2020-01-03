package de.ralfhergert.gw2.modifier;

import de.ralfhergert.generic.value.ValueModifier;
import de.ralfhergert.gw2.model.CharacterAttribute;
import de.ralfhergert.gw2.model.Gw2Character;

import java.time.Duration;
import java.time.temporal.Temporal;

/**
 * This modifier can be used on {@link Temporal} values to modify them more than once.
 */
public class VariableCharacterTimeModifier<T extends Temporal> extends ValueModifier<T,Gw2Character> {

    private Duration duration = Duration.ZERO;

    public VariableCharacterTimeModifier(Object source) {
        super(source);
    }

    public void advance(Duration delta) {
        this.duration = this.duration.plus(delta);
        promoteModification();
    }

    @Override
    public void assignTo(Gw2Character context) {
        // attach as a modifier
        context.getAttribute(CharacterAttribute.CharacterAge).addModifier(this);
    }

    @Override
    public void resignFrom(Gw2Character context) {
        // detach as a modifier
        context.getAttribute(CharacterAttribute.CharacterAge).removeModifier(this);
    }

    @Override
    public T modify(T value, Gw2Character context) {
        return (T)value.plus(duration);
    }
}
