package de.ralfhergert.gw2.modifier;

import de.ralfhergert.generic.value.ValueModifier;
import de.ralfhergert.gw2.model.CharacterAttribute;
import de.ralfhergert.gw2.model.Equipment;
import de.ralfhergert.gw2.model.Gw2Character;

import java.util.stream.Collectors;

/**
 * This modifier is used to have the character equipment as {@link de.ralfhergert.generic.value.Value}.
 */
public class VariableCharacterEquipmentModifier extends ValueModifier<Equipment[],Gw2Character> {

    public VariableCharacterEquipmentModifier(Object source) {
        super(source);
    }

    @Override
    public void assignTo(Gw2Character context) {
        // attach as a modifier
        context.getAttribute(CharacterAttribute.Equipment).addModifier(this);
    }

    @Override
    public void resignFrom(Gw2Character context) {
        // detach as a modifier
        context.getAttribute(CharacterAttribute.Equipment).removeModifier(this);
    }

    @Override
    public Equipment[] modify(Equipment[] value, Gw2Character context) {
        return context.getAssignedEquipment().collect(Collectors.toList()).toArray(new Equipment[]{});
    }
}
