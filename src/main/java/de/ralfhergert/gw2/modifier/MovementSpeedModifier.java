package de.ralfhergert.gw2.modifier;

import de.ralfhergert.generic.value.ValueChangedHandler;
import de.ralfhergert.generic.value.ValueModifier;
import de.ralfhergert.gw2.model.CharacterAttribute;
import de.ralfhergert.gw2.model.Gw2Character;

public class MovementSpeedModifier extends ValueModifier<Number,Gw2Character> {

    private final CharacterAttribute targetAttribute;

    private final ValueChangedHandler<Number,Gw2Character> listener = value -> this.promoteModification();

    public MovementSpeedModifier(Object source, CharacterAttribute targetAttribute) {
        super(source);
        this.targetAttribute = targetAttribute;
    }

    @Override
    public void assignTo(Gw2Character context) {
        // register a listener
        context.getAttribute(CharacterAttribute.MovementSpeed).addChangedHandler(listener);
        // attach as a modifier
        context.getAttribute(targetAttribute).addModifier(this);
    }

    @Override
    public void resignFrom(Gw2Character context) {
        // unregister listener from
        context.getAttribute(CharacterAttribute.MovementSpeed).removeChangedHandler(listener);
        // detach as modifier
        context.getAttribute(targetAttribute).removeModifier(this);
    }

    @Override
    public Number modify(Number value, Gw2Character context) {
        double movementSpeedFactor = context.getAttributeValue(CharacterAttribute.MovementSpeed, Double.class);
        return targetAttribute.getValueType() == Double.class
            ? value.doubleValue() * movementSpeedFactor
            : value.intValue() * movementSpeedFactor;
    }
}
