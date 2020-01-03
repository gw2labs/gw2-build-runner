package de.ralfhergert.gw2.modifier;

import de.ralfhergert.generic.value.ValueChangedHandler;
import de.ralfhergert.generic.value.ValueModifier;
import de.ralfhergert.gw2.model.Gw2Character;
import de.ralfhergert.gw2.model.CharacterAttribute;

public class VitalityToHealthModifier extends ValueModifier<Number,Gw2Character> {

    private final ValueChangedHandler<Number,Gw2Character> listener = value -> this.promoteModification();

    public VitalityToHealthModifier(Object source) {
        super(source);
    }

    @Override
    public void assignTo(Gw2Character context) {
        // register a listener onto the Vitality attribute
        context.getAttribute(CharacterAttribute.Vitality).addChangedHandler(listener);
        // attach as a modifier to the Health attribute
        context.getAttribute(CharacterAttribute.Health).addModifier(this);
    }

    @Override
    public void resignFrom(Gw2Character context) {
        // register a listener onto the Vitality attribute
        context.getAttribute(CharacterAttribute.Vitality).removeChangedHandler(listener);
        // attach as a modifier to the Health attribute
        context.getAttribute(CharacterAttribute.Health).removeModifier(this);
    }

    @Override
    public Number modify(Number value, Gw2Character context) {
        int vitality = context.getAttributeValue(CharacterAttribute.Vitality, Integer.class);
        return value.intValue() + 10 * vitality;
    }
}
