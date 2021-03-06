package de.ralfhergert.gw2.modifier;

import de.ralfhergert.generic.value.ValueChangedHandler;
import de.ralfhergert.generic.value.ValueModifier;
import de.ralfhergert.gw2.model.Gw2Character;
import de.ralfhergert.gw2.model.CharacterAttribute;

public class PrecisionToCriticalHitChanceModifier extends ValueModifier<Number,Gw2Character> {

    private final ValueChangedHandler<Number,Gw2Character> listener = value -> this.promoteModification();

    public PrecisionToCriticalHitChanceModifier(Object source) {
        super(source);
    }

    @Override
    public void assignTo(Gw2Character context) {
        // register a listener onto the Vitality attribute
        context.getAttribute(CharacterAttribute.Precision).addChangedHandler(listener);
        // attach as a modifier to the Health attribute
        context.getAttribute(CharacterAttribute.CriticalChance).addModifier(this);
    }

    @Override
    public void resignFrom(Gw2Character context) {
        // register a listener onto the Vitality attribute
        context.getAttribute(CharacterAttribute.Precision).removeChangedHandler(listener);
        // attach as a modifier to the Health attribute
        context.getAttribute(CharacterAttribute.CriticalChance).removeModifier(this);
    }

    @Override
    public Number modify(Number value, Gw2Character context) {
        final double precision = context.getAttributeValue(CharacterAttribute.Precision, Integer.class);
        return value.doubleValue() + (precision - 1000) / 21 / 100;
    }
}
