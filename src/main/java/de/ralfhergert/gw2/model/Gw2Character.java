package de.ralfhergert.gw2.model;

import de.ralfhergert.gw2.modifier.*;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Gw2Character {

    private final Profession profession;

    private final VariableCharacterTimeModifier<LocalTime> characterTimeModifier = new VariableCharacterTimeModifier<>(this);

    private Map<CharacterAttribute,CharacterAttributeValue> attributes = new HashMap<>();
    private List<StackingBuffOrCondition> buffOrConditions = new ArrayList<>();
    private Map<EquipmentSlot,Equipment<?>> assignedEquipment = new HashMap<>();

    public Gw2Character(Profession profession) {
        this.profession = profession;
        // initialize all character attributes
        Stream.of(CharacterAttribute.values())
            .filter(attributeKey -> attributeKey.getProfession() == null || attributeKey.getProfession().equals(profession))
            .forEach(attributeKey -> attributes.put(attributeKey, new CharacterAttributeValue(attributeKey).setCapValue(attributeKey.getCapValue())));
        // override the health attribute.
        attributes.put(CharacterAttribute.Health, new CharacterAttributeValue(CharacterAttribute.Health, profession.getBaseHealth()));

        characterTimeModifier.assignTo(this);
        // add mechanics
        new VitalityToHealthModifier(this).assignTo(this);
        new ArmorModifier(this).assignTo(this);
        new PrecisionToCriticalHitChanceModifier(this).assignTo(this);
        new FerocityToCriticalDamageModifier(this).assignTo(this);
        new ConcentrationToBoonDurationModifier(this).assignTo(this);
        new MovementSpeedModifier(this, CharacterAttribute.MovementSpeedForwardInCombat).assignTo(this);
        new MovementSpeedModifier(this, CharacterAttribute.MovementSpeedStrafingInCombat).assignTo(this);
        new MovementSpeedModifier(this, CharacterAttribute.MovementSpeedBackwardInCombat).assignTo(this);
        new FuryToCriticalChanceModifier(this).assignTo(this);
        new VigorToEnduranceRegenerationModifier(this).assignTo(this);
    }

    public Profession getProfession() {
        return profession;
    }

    public CharacterAttributeValue getAttribute(CharacterAttribute key) {
        return attributes.get(key);
    }

    public Object getAttributeValue(CharacterAttribute key) {
        return attributes.get(key).getValue(this);
    }

    public <Type> Type getAttributeValue(CharacterAttribute key, Class<Type> clazz) {
        return clazz.cast(attributes.get(key).getValue(this));
    }

    public Range getWeaponStrength() {
        return new Range(900, 1000); // TODO retrieve range from weapon set.
    }

    public boolean hasBuffOrCondition(BuffOrConditionType buffOrConditionType) {
        return buffOrConditions.stream()
            .anyMatch(buffOrCondition -> buffOrCondition.getType() == buffOrConditionType);
    }

    public Stream<StackingBuffOrCondition> getBuffOrConditions() {
        return buffOrConditions.stream();
    }

    public Stream<StackingBuffOrCondition> getBuffOrConditions(BuffOrConditionType buffOrConditionType) {
        return buffOrConditions.stream()
            .filter(buffOrCondition -> buffOrCondition.getType() == buffOrConditionType);
    }

    public Gw2Character addBuffOrCondition(StackingBuffOrCondition buffOrCondition) {
        buffOrConditions.add(buffOrCondition);
        attributes.get(CharacterAttribute.EffectingBuffOrConditions).markAsModified();
        return this;
    }

    public Gw2Character removeBuffOrCondition(StackingBuffOrCondition buffOrCondition) {
        buffOrConditions.remove(buffOrCondition);
        attributes.get(CharacterAttribute.EffectingBuffOrConditions).markAsModified();
        return this;
    }

    public Gw2Character equip(Equipment<?> piece) {
        final Equipment<?> previousEquipment = assignedEquipment.put(piece.getSlot(), piece);
        if (previousEquipment != null) {
            previousEquipment.getModifiers().forEach(modifier -> modifier.resignFrom(this));
        }
        piece.getModifiers().forEach(modifier -> modifier.assignTo(this));
        return this;
    }

    public Gw2Character advance(TemporalAmount delta) {
        characterTimeModifier.advance(Duration.from(delta));
        return this;
    }
}