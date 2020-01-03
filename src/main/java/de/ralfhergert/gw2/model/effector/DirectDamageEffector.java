package de.ralfhergert.gw2.model.effector;

import de.ralfhergert.gw2.model.BuffOrConditionType;
import de.ralfhergert.gw2.model.CharacterAttribute;
import de.ralfhergert.gw2.model.Gw2Character;
import de.ralfhergert.gw2.modifier.DirectDamageHealthModifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.util.Random;

/**
 * This effector does calculate the direct damage done from a source to a target for a
 * <b>successful attack</b>. An attack can be unsuccessful due to blindness, block,
 * evade, out-of-range, out-of-sight or invulnerability. But all of these are not taken
 * into account by this class and have to be checked before.
 *
 * @see <a href="https://wiki.guildwars2.com/wiki/Damage#Direct_damage">Direct Damage</a>
 */
public class DirectDamageEffector implements TargetedEffector {

    private static final Logger LOG = LoggerFactory.getLogger("Combat");
    private static final Random RNG = new Random();

    private final float coefficient;

    public DirectDamageEffector(float coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public void perform(Gw2Character source, Gw2Character target, LocalTime time) {
        final int weaponStrength = source.getWeaponStrength().random();

        final DirectDamageHealthModifier mod = new DirectDamageHealthModifier(source, target, time)
            .withBaseDamage(Math.round(weaponStrength * source.getAttributeValue(CharacterAttribute.Power, Integer.class) *
                coefficient / target.getAttributeValue(CharacterAttribute.Armor, Integer.class)))
            .withIsCriticalHit(RNG.nextDouble() < source.getAttributeValue(CharacterAttribute.CriticalChance, Double.class))
            .withIsGlacialHit(source.hasBuffOrCondition(BuffOrConditionType.Weakness) && RNG.nextDouble() < 0.5)
            .withNumberOfVulnerabilities((int)Math.min(25, target.getBuffOrConditions(BuffOrConditionType.Vulnerability).count()))
            .withHasProtection(target.hasBuffOrCondition(BuffOrConditionType.Protection));

        int damage = mod.isCriticalHit()
            ? (int)Math.round(mod.getBaseDamage() * source.getAttributeValue(CharacterAttribute.CriticalDamage, Double.class))
            : mod.getBaseDamage();

        if (mod.isGlacialHit()) {
            damage /= 2;
        }

        final double vulnerabilityFactor = 1 + 0.01 * mod.getNumberOfVulnerabilities();
        damage *= vulnerabilityFactor;

        if (mod.hasProtection()) {
            damage = damage * 2 / 3;
        }

        mod.withFinalDamage(damage)
            .assignTo(target);
    }
}
