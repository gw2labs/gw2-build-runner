package de.ralfhergert.gw2.model;

import de.ralfhergert.generic.value.Assignable;
import de.ralfhergert.generic.value.ValueChangedHandler;
import de.ralfhergert.gw2.modifier.CharacterAttributeModifier;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class StackingBuffOrCondition implements Assignable<Gw2Character> {

    private final ValueChangedHandler<LocalTime,Gw2Character> characterTimeListener = (time) -> checkTimeToLive(time.getValue());
    private final ValueChangedHandler<Double,Gw2Character> durationWearOffListener;

    private final List<CharacterAttributeModifier> modifiers = new ArrayList<>();

    private final BuffOrConditionType type;
    private final int wantedNumberOfStacks;
    private final Duration duration;
    private final Gw2Character sourceCharacter;

    private Gw2Character targetCharacter;
    private LocalTime applicationTime = null;
    private int appliedNumberOfStacks = 0;

    private double durationWearOff = 1;
    private Duration remainingDuration;
    private LocalTime remainingDurationMeasured;

    public StackingBuffOrCondition(BuffOrConditionType type, int wantedNumberOfStacks, Duration duration, Gw2Character sourceCharacter) {
        this.type = type;
        this.wantedNumberOfStacks = wantedNumberOfStacks;
        this.duration = duration;
        this.sourceCharacter = sourceCharacter;

        if (type.getEffectType() == EffectType.Boon) {
            durationWearOffListener = (boonDuration) -> durationWearOff = 1 / (1 + boonDuration.getValue());
        } else if (type.getEffectType() == EffectType.Condition) {
            durationWearOffListener = (conditionDuration) -> durationWearOff = 1 + conditionDuration.getValue();
        } else {
            durationWearOffListener = null;
        }
    }

    public BuffOrConditionType getType() {
        return type;
    }

    public int getWantedNumberOfStacks() {
        return wantedNumberOfStacks;
    }

    public Duration getDuration() {
        return duration;
    }

    public int getAppliedNumberOfStacks() {
        return appliedNumberOfStacks;
    }

    protected abstract Collection<CharacterAttributeModifier> createModifiersFor(int numberOfStacks);

    @Override
    public void assignTo(Gw2Character gw2Character) {
        final int numberOfAppliedStacks = gw2Character
            .getBuffOrConditions(getType())
            .map(StackingBuffOrCondition::getAppliedNumberOfStacks)
            .reduce(0, Integer::sum);

        appliedNumberOfStacks = Math.min(getWantedNumberOfStacks(), getType().getMaxNumberOfStacks() - numberOfAppliedStacks);

        if (appliedNumberOfStacks <= 0) {
            return; // don't apply this buff or condition.
        }

        this.targetCharacter = gw2Character;
        gw2Character.getAttribute(CharacterAttribute.CharacterAge).addChangedHandler(characterTimeListener);
        switch (type.getEffectType()) {
            case Boon:
                gw2Character.getAttribute(CharacterAttribute.BoonDuration).addChangedHandler(durationWearOffListener);
                break;
            case Condition:
                gw2Character.getAttribute(CharacterAttribute.ConditionDuration).addChangedHandler(durationWearOffListener);
                break;
            default: /* fall-through */
        }

        applicationTime = gw2Character.getAttributeValue(CharacterAttribute.CharacterAge, LocalTime.class);
        remainingDuration = Duration.from(duration);
        remainingDurationMeasured = applicationTime;

        gw2Character.addBuffOrCondition(this);

        // create all necessary modifiers.
        modifiers.addAll(createModifiersFor(appliedNumberOfStacks));
        // apply all modifiers
        modifiers.forEach(modifier -> modifier.assignTo(gw2Character));
    }

    @Override
    public void resignFrom(Gw2Character gw2Character) {
        this.targetCharacter = null;
        gw2Character.getAttribute(CharacterAttribute.CharacterAge).removeChangedHandler(characterTimeListener);
        gw2Character.getAttribute(CharacterAttribute.BoonDuration).removeChangedHandler(durationWearOffListener);
        gw2Character.getAttribute(CharacterAttribute.ConditionDuration).removeChangedHandler(durationWearOffListener);
        gw2Character.removeBuffOrCondition(this);
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
            resignFrom(targetCharacter);
        }
    }
}
