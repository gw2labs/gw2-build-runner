package de.ralfhergert.gw2.model;

import de.ralfhergert.generic.value.Assignable;

public abstract class StackingBuffOrCondition implements Assignable<Gw2Character> {

    private final BuffOrConditionType type;
    private final int wantedNumberOfStacks;

    protected int appliedNumberOfStacks = 0;

    public StackingBuffOrCondition(BuffOrConditionType type, int wantedNumberOfStacks) {
        this.type = type;
        this.wantedNumberOfStacks = wantedNumberOfStacks;
    }

    public BuffOrConditionType getType() {
        return type;
    }

    public int getWantedNumberOfStacks() {
        return wantedNumberOfStacks;
    }

    public int getAppliedNumberOfStacks() {
        return appliedNumberOfStacks;
    }
}
