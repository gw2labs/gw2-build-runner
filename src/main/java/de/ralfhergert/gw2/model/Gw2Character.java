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

/**
 * This class represents a Guild Wars 2 (player) character with all its
 * attributes, equipment, specializations and traits.
 */
public class Gw2Character {

    private final Profession profession;

    private final VariableCharacterTimeModifier<LocalTime> characterTimeModifier = new VariableCharacterTimeModifier<>(this);
    private final VariableCharacterEquipmentModifier characterEquipmentModifier = new VariableCharacterEquipmentModifier(this);
    private final GenericCharacterAttributeModifier<Boolean> characterInCombatModifier = new GenericCharacterAttributeModifier<>(this, CharacterAttribute.InCombat);

    private Map<CharacterAttribute,CharacterAttributeValue> attributes = new HashMap<>();
    private List<StackingBuffOrCondition> buffOrConditions = new ArrayList<>();
    private Map<EquipmentSlot,Equipment<?>> assignedEquipment = new HashMap<>();

    private World world;
    private Team team;
    private WorldPosition position;

    public Gw2Character(Profession profession) {
        this.profession = profession;
        // initialize all character attributes
        Stream.of(CharacterAttribute.values())
            .filter(attributeKey -> attributeKey.getProfession() == null || attributeKey.getProfession().equals(profession))
            .forEach(attributeKey -> attributes.put(attributeKey, new CharacterAttributeValue(attributeKey, this).setCapValue(attributeKey.getCapValue())));
        // override the health attribute.
        attributes.put(CharacterAttribute.Health, new CharacterAttributeValue(CharacterAttribute.Health, profession.getBaseHealth(), this));

        characterTimeModifier.assignTo(this);
        characterEquipmentModifier.assignTo(this);
        characterInCombatModifier.assignTo(this);

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

        new RuneModifier().assignTo(this);
    }

    public Profession getProfession() {
        return profession;
    }

    public CharacterAttributeValue getAttribute(CharacterAttribute key) {
        return attributes.get(key);
    }

    public Object getAttributeValue(CharacterAttribute key) {
        return attributes.get(key).getValue();
    }

    public <Type> Type getAttributeValue(CharacterAttribute key, Class<Type> clazz) {
        return clazz.cast(attributes.get(key).getValue());
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

    public Stream<Equipment<?>> getAssignedEquipment() {
        return assignedEquipment.values().stream();
    }

    public Gw2Character equip(Equipment<?> piece) {
        final Equipment<?> previousEquipment = assignedEquipment.put(piece.getSlot(), piece);
        if (previousEquipment != null) {
            previousEquipment.getModifiers().forEach(modifier -> modifier.resignFrom(this));
        }
        piece.getModifiers().forEach(modifier -> modifier.assignTo(this));
        attributes.get(CharacterAttribute.Equipment).markAsModified();
        return this;
    }

    public Gw2Character advance(TemporalAmount delta) {
        characterTimeModifier.advance(Duration.from(delta));
        return this;
    }

    public Gw2Character setInCombat(boolean inCombat) {
        characterInCombatModifier.setValue(inCombat);
        return this;
    }

    public World getWorld() {
        return world;
    }

    public Gw2Character setWorld(World world) {
        this.world = world;
        return this;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public WorldPosition getPosition() {
        return position;
    }

    public void setPosition(WorldPosition position) {
        this.position = position;
    }
}
