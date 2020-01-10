package de.ralfhergert.gw2.model;

import de.ralfhergert.generic.value.Assignable;
import de.ralfhergert.generic.value.ValueChangedHandler;
import de.ralfhergert.gw2.modifier.CharacterAttributeModifier;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class IrremovableBuffOrCondition implements Assignable<Gw2Character> {

    private final ValueChangedHandler<LocalTime,Gw2Character> characterTimeListener = (time) -> checkTimeToLive(time.getValue());
    private final ValueChangedHandler<Double,Gw2Character> durationWearOffListener;

    private final List<CharacterAttributeModifier> modifiers = new ArrayList<>();

    private final BuffOrConditionType type;
    private final Duration duration;

    private Gw2Character gw2Character;
    private LocalTime applicationTime = null;

    private double durationWearOff = 1;
    private Duration remainingDuration;
    private LocalTime remainingDurationMeasured;

    public IrremovableBuffOrCondition(BuffOrConditionType type, Duration duration) {
        this.type = type;
        this.duration = duration;

        if (type.getEffectType() == EffectType.Aura) {
            durationWearOffListener = (auraDuration) -> durationWearOff = 1 / (1 + auraDuration.getValue());
        } else {
            durationWearOffListener = null;
        }
    }

    public BuffOrConditionType getType() {
        return type;
    }

    public Duration getDuration() {
        return duration;
    }

    protected abstract Collection<CharacterAttributeModifier> createModifiersFor();

    @Override
    public void assignTo(Gw2Character gw2Character) {
        this.gw2Character = gw2Character;
        gw2Character.getAttribute(CharacterAttribute.CharacterAge).addChangedHandler(characterTimeListener);
        if (type.getEffectType() == EffectType.Aura) {
            gw2Character.getAttribute(CharacterAttribute.AuraDuration).addChangedHandler(durationWearOffListener);
        }

        applicationTime = gw2Character.getAttributeValue(CharacterAttribute.CharacterAge, LocalTime.class);
        remainingDuration = Duration.from(duration);
        remainingDurationMeasured = applicationTime;

        // TODO impl: gw2Character.addBuffOrCondition(this);

        // create all necessary modifiers.
        modifiers.addAll(createModifiersFor());
        // apply all modifiers
        modifiers.forEach(modifier -> modifier.assignTo(gw2Character));
    }

    @Override
    public void resignFrom(Gw2Character gw2Character) {
        this.gw2Character = null;
        gw2Character.getAttribute(CharacterAttribute.CharacterAge).removeChangedHandler(characterTimeListener);
        gw2Character.getAttribute(CharacterAttribute.AuraDuration).removeChangedHandler(durationWearOffListener);
        // TODO impl: gw2Character.removeBuffOrCondition(this);
        modifiers.forEach(modifier -> modifier.resignFrom(gw2Character));
    }

    private void checkTimeToLive(LocalTime characterTime) {
        Duration timePast = Duration.between(remainingDurationMeasured, characterTime);
        if (timePast.isNegative()) {
            return;
        }
        remainingDuration = remainingDuration.minus(timePast.multipliedBy((long)(1000 * durationWearOff)).dividedBy(1000));
        remainingDurationMeasured = characterTime;

        if (remainingDuration.isNegative() || remainingDuration.isZero()) {
            resignFrom(gw2Character);
        }
    }
}
