package de.ralfhergert.gw2.modifier;

import de.ralfhergert.generic.value.ValueChangedHandler;
import de.ralfhergert.generic.value.ValueModifier;
import de.ralfhergert.gw2.model.CharacterAttribute;
import de.ralfhergert.gw2.model.Gw2Character;

/**
 * Armor is the sum of defence and toughness.
 *
 * @see <a href="https://wiki.guildwars2.com/wiki/Armor_(attribute)">Armor</a>
 */
public class ArmorModifier extends ValueModifier<Number,Gw2Character> {

    private final ValueChangedHandler<Number,Gw2Character> listener = value -> this.promoteModification();

    public ArmorModifier(Object source) {
        super(source);
    }

    @Override
    public void assignTo(Gw2Character context) {
        // register a listener onto the Vitality attribute
        context.getAttribute(CharacterAttribute.Defense).addChangedHandler(listener);
        context.getAttribute(CharacterAttribute.Toughness).addChangedHandler(listener);
        // attach as a modifier to the Health attribute
        context.getAttribute(CharacterAttribute.Armor).addModifier(this);
    }

    @Override
    public void resignFrom(Gw2Character context) {
        // register a listener onto the Vitality attribute
        context.getAttribute(CharacterAttribute.Defense).removeChangedHandler(listener);
        context.getAttribute(CharacterAttribute.Toughness).removeChangedHandler(listener);
        // attach as a modifier to the Health attribute
        context.getAttribute(CharacterAttribute.Armor).removeModifier(this);
    }

    @Override
    public Number modify(Number value, Gw2Character context) {
        return value.intValue()
            + context.getAttributeValue(CharacterAttribute.Defense, Integer.class)
            + context.getAttributeValue(CharacterAttribute.Toughness, Integer.class);
    }
}
