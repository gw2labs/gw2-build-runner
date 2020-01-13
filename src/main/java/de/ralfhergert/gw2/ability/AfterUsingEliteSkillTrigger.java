package de.ralfhergert.gw2.ability;

import de.ralfhergert.generic.function.Triggerable;
import de.ralfhergert.generic.value.Assignable;
import de.ralfhergert.generic.value.ValueChangedHandler;
import de.ralfhergert.gw2.model.CharacterAttribute;
import de.ralfhergert.gw2.model.Gw2Character;

import java.time.Duration;
import java.time.LocalTime;

/**
 * This trigger is fires after an elite skill is being used.
 *
 * TODO add a listener to get the "last skill being used".
 */
public class AfterUsingEliteSkillTrigger implements Assignable<Gw2Character> {

    private final ValueChangedHandler<Object,Gw2Character> changeListener = value -> this.check(value.getOwner());

    private final Duration cooldown;
    private final Triggerable<Gw2Character> triggerable;

    private LocalTime lastTriggered = null;

    public AfterUsingEliteSkillTrigger(Duration cooldown, Triggerable<Gw2Character> triggerable) {
        this.cooldown = cooldown;
        this.triggerable = triggerable;
    }

    @Override
    public void assignTo(Gw2Character gw2Character) {
        gw2Character.getAttribute(CharacterAttribute.CharacterAge).addChangedHandler(changeListener);
    }

    @Override
    public void resignFrom(Gw2Character gw2Character) {
        gw2Character.getAttribute(CharacterAttribute.CharacterAge).removeChangedHandler(changeListener);
    }

    private void check(Gw2Character gw2Character) {
        if (gw2Character == null) {
            return;
        }
        final LocalTime currentCharacterTime = gw2Character.getAttributeValue(CharacterAttribute.CharacterAge, LocalTime.class);
        if (lastTriggered != null && lastTriggered.plus(cooldown).isAfter(currentCharacterTime)) {
            return;
        }
        lastTriggered = currentCharacterTime;
        triggerable.trigger(gw2Character);
    }
}
