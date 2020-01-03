package de.ralfhergert.gw2.model;

/**
 * Compare with
 * @see <a href="https://wiki.guildwars2.com/wiki/Effect_stacking#List_of_duration-stacking_effects">List of Duartion Stacking Effects</>
 * @see <a href="https://wiki.guildwars2.com/wiki/Effect_stacking#List_of_intensity-stacking_effects">List of Intensity stacking Effects</>
 */
public enum BuffOrConditionType {
    Aegis(EffectType.Boon, EffectStackingType.Duration, 9),
    Alacrity(EffectType.Boon, EffectStackingType.Duration, 9),
    Bleeding(EffectType.Condition, EffectStackingType.Intensity, 1500),
    Blinded(EffectType.Condition, EffectStackingType.Duration, 9),
    Burning(EffectType.Condition, EffectStackingType.Intensity, 1500),
    Chilled(EffectType.Condition, EffectStackingType.Duration, 5),
    Confusion(EffectType.Condition, EffectStackingType.Intensity, 1500),
    Crippled(EffectType.Condition, EffectStackingType.Duration, 5),
    Fury(EffectType.Boon, EffectStackingType.Duration, 9), // 3min
    Fear(EffectType.Condition, EffectStackingType.Duration, 5),
    Immobile(EffectType.Condition, EffectStackingType.Duration, 3),
    Might(EffectType.Boon, EffectStackingType.Intensity, 25),
    Poisoned(EffectType.Condition, EffectStackingType.Intensity, 1500),
    Protection(EffectType.Boon, EffectStackingType.Duration, 5),
    Quickness(EffectType.Boon, EffectStackingType.Duration, 5),
    Regeneration(EffectType.Boon, EffectStackingType.Duration, 5),
    Resistance(EffectType.Boon, EffectStackingType.Duration, 5),
    Retaliation(EffectType.Boon, EffectStackingType.Duration, 5),
    Slow(EffectType.Condition, EffectStackingType.Duration, 9),
    Stability(EffectType.Boon, EffectStackingType.Intensity, 25),
    Swiftness(EffectType.Boon, EffectStackingType.Duration, 9),
    Taunt(EffectType.Condition, EffectStackingType.Duration, Integer.MAX_VALUE),
    Torment(EffectType.Condition, EffectStackingType.Intensity, 1500),
    Vigor(EffectType.Boon, EffectStackingType.Duration, 5),
    Vulnerability(EffectType.Condition, EffectStackingType.Intensity, 25),
    Weakness(EffectType.Condition, EffectStackingType.Duration, 5);

    private final EffectType effectType;
    private final EffectStackingType stackingType;
    private int maxNumberOfStacks;

    BuffOrConditionType(EffectType effectType, EffectStackingType stackingType, int maxNumberOfStacks) {
        this.effectType = effectType;
        this.stackingType = stackingType;
        this.maxNumberOfStacks = maxNumberOfStacks;
    }

    public EffectType getEffectType() {
        return effectType;
    }

    public EffectStackingType getStackingType() {
        return stackingType;
    }

    public int getMaxNumberOfStacks() {
        return maxNumberOfStacks;
    }
}
