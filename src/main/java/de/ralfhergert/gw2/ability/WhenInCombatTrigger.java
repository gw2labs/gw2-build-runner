package de.ralfhergert.gw2.ability;

import de.ralfhergert.generic.function.Triggerable;
import de.ralfhergert.generic.value.Assignable;
import de.ralfhergert.generic.value.ValueChangedHandler;
import de.ralfhergert.gw2.model.CharacterAttribute;
import de.ralfhergert.gw2.model.Gw2Character;

import java.time.Duration;
import java.time.LocalTime;

public class WhenInCombatTrigger implements Assignable<Gw2Character> {

    private final ValueChangedHandler<Object,Gw2Character> changeListener = value -> this.check();

    private final Duration cooldown;
    private final Triggerable triggerable;

    private Gw2Character gw2Character = null;
    private LocalTime lastTriggered = null;

    public WhenInCombatTrigger(Duration cooldown, Triggerable triggerable) {
        this.cooldown = cooldown;
        this.triggerable = triggerable;
    }

    @Override
    public void assignTo(Gw2Character gw2Character) {
        this.gw2Character = gw2Character;
        gw2Character.getAttribute(CharacterAttribute.InCombat).addChangedHandler(changeListener);
        gw2Character.getAttribute(CharacterAttribute.CharacterAge).addChangedHandler(changeListener);
    }

    @Override
    public void resignFrom(Gw2Character gw2Character) {
        this.gw2Character = null;
        gw2Character.getAttribute(CharacterAttribute.InCombat).removeChangedHandler(changeListener);
        gw2Character.getAttribute(CharacterAttribute.CharacterAge).removeChangedHandler(changeListener);
    }

    private void check() {
        if (gw2Character == null) {
            return;
        }
        if (!gw2Character.getAttributeValue(CharacterAttribute.InCombat, Boolean.class)) {
            return;
        }
        final LocalTime currentCharacterTime = gw2Character.getAttributeValue(CharacterAttribute.CharacterAge, LocalTime.class);
        if (lastTriggered != null && lastTriggered.plus(cooldown).isAfter(currentCharacterTime)) {
            return;
        }
        lastTriggered = currentCharacterTime;
        triggerable.trigger();
    }
}
