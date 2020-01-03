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

    private final ValueChangedHandler<LocalTime,Gw2Character> timeToLiveListener = (v) -> checkTimeToLive();
    private final List<CharacterAttributeModifier> modifiers = new ArrayList<>();

    private final BuffOrConditionType type;
    private final int wantedNumberOfStacks;
    private final Duration duration;

    private Gw2Character gw2Character;
    private LocalTime applicationTime = null;
    private int appliedNumberOfStacks = 0;

    private Duration remainingDuration;
    private LocalTime remainingDurationMeasured;

    public StackingBuffOrCondition(BuffOrConditionType type, int wantedNumberOfStacks, Duration duration) {
        this.type = type;
        this.wantedNumberOfStacks = wantedNumberOfStacks;
        this.duration = duration;
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

        this.gw2Character = gw2Character;
        gw2Character.getAttribute(CharacterAttribute.CharacterAge).addChangedHandler(timeToLiveListener);

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
        this.gw2Character = null;
        gw2Character.getAttribute(CharacterAttribute.CharacterAge).removeChangedHandler(timeToLiveListener);
        gw2Character.removeBuffOrCondition(this);
        modifiers.forEach(modifier -> modifier.resignFrom(gw2Character));
    }

    private void checkTimeToLive() {
        if (gw2Character == null) {
            return;
        }
        final LocalTime characterTime = gw2Character.getAttributeValue(CharacterAttribute.CharacterAge, LocalTime.class);
        if (characterTime.isAfter(remainingDurationMeasured.plus(remainingDuration))) {
            resignFrom(gw2Character);
        }
    }
}
