package de.ralfhergert.gw2.modifier;

import de.ralfhergert.generic.value.ValueChangedHandler;
import de.ralfhergert.generic.value.ValueModifier;
import de.ralfhergert.gw2.model.CharacterAttribute;
import de.ralfhergert.gw2.model.Gw2Character;

public class ConcentrationToBoonDurationModifier extends ValueModifier<Number,Gw2Character> {

    private final ValueChangedHandler<Number,Gw2Character> listener = value -> this.promoteModification();

    public ConcentrationToBoonDurationModifier(Object source) {
        super(source);
    }

    @Override
    public void assignTo(Gw2Character context) {
        // register a listener onto the Vitality attribute
        context.getAttribute(CharacterAttribute.Concentration).addChangedHandler(listener);
        // attach as a modifier to the Health attribute
        context.getAttribute(CharacterAttribute.BoonDuration).addModifier(this);
    }

    @Override
    public void resignFrom(Gw2Character context) {
        // register a listener onto the Vitality attribute
        context.getAttribute(CharacterAttribute.Concentration).removeChangedHandler(listener);
        // attach as a modifier to the Health attribute
        context.getAttribute(CharacterAttribute.BoonDuration).removeModifier(this);
    }

    @Override
    public Number modify(Number value, Gw2Character context) {
        final double concentration = context.getAttributeValue(CharacterAttribute.Concentration, Integer.class);
        return value.doubleValue() + concentration / 15 / 100;
    }
}
