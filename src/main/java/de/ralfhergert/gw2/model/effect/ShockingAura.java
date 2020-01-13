package de.ralfhergert.gw2.model.effect;

import de.ralfhergert.gw2.model.BuffOrConditionType;
import de.ralfhergert.gw2.model.Gw2Character;
import de.ralfhergert.gw2.model.IrremovableBuffOrCondition;
import de.ralfhergert.gw2.modifier.CharacterAttributeModifier;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;

/**
 * Stuns nearby attacking foes with an electric shock (only once per 2 seconds for each attacker).
 * Radius is 240 units.
 */
public class ShockingAura extends IrremovableBuffOrCondition {

    public ShockingAura(Duration duration, Gw2Character sourceCharacter) {
        super(BuffOrConditionType.Might, duration, sourceCharacter);
    }

    @Override
    protected Collection<CharacterAttributeModifier> createModifiersFor() {
        return Collections.emptyList(); // TODO impl
    }
}
