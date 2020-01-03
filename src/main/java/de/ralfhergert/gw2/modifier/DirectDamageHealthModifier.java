package de.ralfhergert.gw2.modifier;

import de.ralfhergert.generic.value.ValueModifier;
import de.ralfhergert.gw2.model.CharacterAttribute;
import de.ralfhergert.gw2.model.Gw2Character;

import java.time.LocalTime;

public class DirectDamageHealthModifier extends ValueModifier<Number,Gw2Character> {

    private final Gw2Character source;
    private final Gw2Character target;
    private final LocalTime time;

    private int baseDamage = 0;
    private boolean isCriticalHit = false;
    private boolean isGlacialHit = false;
    private int numberOfVulnerabilities = 0;
    private boolean hasProtection = false;
    private int finalDamage = 0;

    public DirectDamageHealthModifier(Gw2Character source, Gw2Character target, LocalTime time) {
        super(source);
        this.source = source;
        this.target = target;
        this.time = time;
    }

    @Override
    public void assignTo(Gw2Character target) {
        target
            .getAttribute(CharacterAttribute.Health)
            .addChangedHandler(value -> this.promoteModification());
    }

    @Override
    public void resignFrom(Gw2Character context) {
        throw new UnsupportedOperationException("direct damage is not supported to be unassigned");
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public DirectDamageHealthModifier withBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
        return this;
    }

    public boolean isCriticalHit() {
        return isCriticalHit;
    }

    public DirectDamageHealthModifier withIsCriticalHit(boolean isCriticalHit) {
        this.isCriticalHit = isCriticalHit;
        return this;
    }

    public boolean isGlacialHit() {
        return isGlacialHit;
    }

    public DirectDamageHealthModifier withIsGlacialHit(boolean isGlacialHit) {
        this.isGlacialHit = isGlacialHit;
        return this;
    }

    public int getNumberOfVulnerabilities() {
        return numberOfVulnerabilities;
    }

    public DirectDamageHealthModifier withNumberOfVulnerabilities(int number) {
        this.numberOfVulnerabilities = number;
        return this;
    }

    public boolean hasProtection() {
        return hasProtection;
    }

    public DirectDamageHealthModifier withHasProtection(boolean hasProtection) {
        this.hasProtection = hasProtection;
        return this;
    }

    public int getFinalDamage() {
        return finalDamage;
    }

    public DirectDamageHealthModifier withFinalDamage(int damage) {
        this.finalDamage = damage;
        return this;
    }

    @Override
    public Number modify(Number value, Gw2Character context) {
        return value.intValue() - finalDamage;
    }
}
