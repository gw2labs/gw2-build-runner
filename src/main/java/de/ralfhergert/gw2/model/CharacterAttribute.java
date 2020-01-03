package de.ralfhergert.gw2.model;

import java.time.LocalTime;

public enum CharacterAttribute {
    // Primary attributes
    Power(Integer.class, null, 1000, null, "Increases outgoing direct damage"),
    Precision(Integer.class, null, 1000, null, "Increases Critical Chance"),
    Toughness(Integer.class, null, 1000, null, "Increases Armor"),
    Vitality(Integer.class, null, 1000, null, "Increases Health"),
    // Secondary attributes
    Concentration(Integer.class, null, 0, null, "Increases Boon duration"),
    ConditionDamage(Integer.class, null, 0, null, "Increases damage done by conditions"),
    Expertise(Integer.class, null, 0, null, "Increases condition duration"),
    Ferocity(Integer.class, null, 0, null, "Increases critical damage"),
    HealingPower(Integer.class, null, 0, null, "Increases outgoing healing and regeneration"),
    // Derived attributes
    Armor(Integer.class, null, 0, null, "Decreases incoming direct damage"),
    BoonDuration(Double.class, null, 0d, 1d, "Increase the duration of all applied boons"),
    CriticalChance(Double.class, null, 0.05, 1d, "Increase the critical hit chance"),
    CriticalDamage(Double.class, null, 1.5, null, "Increase the critical hit damage"),
    ConditionDuration(Double.class, null, 0d, 1d, "Increase the duration of all inflicted conditions"),
    Health(Integer.class, null, 0, null, "The character's maximum health"),
    // Profession attributes
    AttunementRechargeRate(Double.class, Profession.Elementalist, 0d, null, "Reduces the recharge rate of the four elemental attunements"),
    ShatterSkillRechargeRate(Double.class, Profession.Mesmer, 0d, null, "Reduces the recharge rate of all shatter skills"),
    LifeForcePool(Double.class, Profession.Necromancer, 0d, null, "Increases the size of the necromancer's life force pool"),
    ToolBeltRechargeRate(Double.class, Profession.Engineer, 0d, null, "Reduces the recharge rate of all tool belt skill"),
    PetAttributeBonus(Integer.class, Profession.Ranger, 0, null, "Increase pet attributes"),
    StealRechargeRate(Double.class, Profession.Thief, 0d, null, "Reduces the recharge rate of the steal ability"),
    VirtueRechargeRate(Double.class, Profession.Guardian, 0d, null, "Reduces the recharge rate of all virtues"),
    BurstRechargeRate(Double.class, Profession.Warrior, 0d, null, "Reduces the recharge rate of all burst skills"),
    // Revenants have no class specific attribute
    // Other attributes
    //WeaponStrength(Integer.class, null, "Increases outgoing direct damage"),
    Defense(Integer.class, null, 0, null, "Increases armor"),
    Endurance(Integer.class, null, 100, null, "The character's maximum endurance"),
    EnduranceRegeneration(Double.class, null, 0.05, 0.1, "Endurance regeneration"),
    MovementSpeed(Double.class, null, 1.0, null, "The character's movement speed multiplier"),
    MovementSpeedForwardInCombat(Integer.class, null, 210, 400, "The character's maximum movement speed"),
    MovementSpeedStrafingInCombat(Integer.class, null, 180, 400, "The character's maximum movement speed"),
    MovementSpeedBackwardInCombat(Integer.class, null, 105, 400, "The character's maximum movement speed"),
    OutgoingHealingEffectiveness(Double.class, null, 0d, 400d, "Improves healing done to others"),
    // dynamic state attributes
    InCombat(Boolean.class, null, false, null, "indicates whether the character is in combat"),
    CharacterAge(LocalTime.class, null, LocalTime.of(0, 0, 0, 0), null, "current timestamp the character is at"),
    EffectingBuffOrConditions(StackingBuffOrCondition[].class, null, null, null, "attribute is used to signal when buffs or condition are un/applied");

    private final Class<?> valueType;
    private final Profession profession;
    private final Object baseValue;
    private final Object capValue;
    private final String description;

    <Type> CharacterAttribute(Class<Type> valueType, Profession profession, Type baseValue, Type capValue, String description) {
        this.valueType = valueType;
        this.profession = profession;
        this.baseValue = baseValue;
        this.capValue = capValue;
        this.description = description;
    }

    public Class<?> getValueType() {
        return valueType;
    }

    /**
     * For which {@link Profession} this attributes may be valid.
     * If null this attributes in meant for all character classes.
     */
    public Profession getProfession() {
        return profession;
    }

    public Object getBaseValue() {
        return baseValue;
    }

    public Object getCapValue() {
        return capValue;
    }

    public String getDescription() {
        return description;
    }
}
