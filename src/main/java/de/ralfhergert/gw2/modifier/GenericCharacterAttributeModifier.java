package de.ralfhergert.gw2.modifier;

import de.ralfhergert.generic.value.ValueModifier;
import de.ralfhergert.gw2.model.CharacterAttribute;
import de.ralfhergert.gw2.model.Gw2Character;

import java.util.Objects;

/**
 * This modifier can be used to set a character attribute value.
 */
public class GenericCharacterAttributeModifier<Type> extends ValueModifier<Type,Gw2Character> {

    private final CharacterAttribute attribute;

    private Type value;

    public GenericCharacterAttributeModifier(Object source, CharacterAttribute attribute) {
        super(source);
        this.attribute = attribute;
    }

    public void setValue(Type value) {
        if (!Objects.equals(this.value, value)) {
            this.value = value;
            promoteModification();
        }
    }

    @Override
    public void assignTo(Gw2Character context) {
        // attach as a modifier
        context.getAttribute(attribute).addModifier(this);
        value = (Type)context.getAttributeValue(attribute);
    }

    @Override
    public void resignFrom(Gw2Character context) {
        // detach as a modifier
        context.getAttribute(attribute).removeModifier(this);
    }

    @Override
    public Type modify(Type value, Gw2Character context) {
        return this.value;
    }
}
