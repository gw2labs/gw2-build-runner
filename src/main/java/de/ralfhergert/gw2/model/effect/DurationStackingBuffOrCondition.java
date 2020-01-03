package de.ralfhergert.gw2.model.effect;

import de.ralfhergert.gw2.model.Gw2Character;
import de.ralfhergert.gw2.model.StackingBuffOrCondition;
import de.ralfhergert.gw2.model.BuffOrConditionType;
import de.ralfhergert.gw2.modifier.CharacterAttributeModifier;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class DurationStackingBuffOrCondition extends StackingBuffOrCondition {

    private final List<CharacterAttributeModifier> modifiers = new ArrayList<>();

    private final Duration duration;
    private final Duration durationCap;

    private LocalTime applicationTime = null;

    public DurationStackingBuffOrCondition(BuffOrConditionType type, int wantedNumberOfStacks, Duration duration, Duration durationCap) {
        super(type, wantedNumberOfStacks);
        this.duration = duration;
        this.durationCap = durationCap;
    }

    public Duration getDuration() {
        return duration;
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
            return;
        }
        gw2Character.addBuffOrCondition(this);

        // create all necessary modifiers.
        modifiers.addAll(createModifiersFor(appliedNumberOfStacks));
        // apply all modifiers
        modifiers.forEach(modifier -> modifier.assignTo(gw2Character));
    }

    @Override
    public void resignFrom(Gw2Character gw2Character) {
        modifiers.forEach(modifier -> modifier.resignFrom(gw2Character));
    }
}
