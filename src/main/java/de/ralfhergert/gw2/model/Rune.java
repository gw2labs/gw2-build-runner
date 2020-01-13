package de.ralfhergert.gw2.model;

import de.ralfhergert.generic.function.TargetedTriggerableGroup;
import de.ralfhergert.generic.number.IncreaseByInt;
import de.ralfhergert.generic.number.ScaleOperation;
import de.ralfhergert.generic.value.Assignable;
import de.ralfhergert.generic.value.AssignableGroup;
import de.ralfhergert.gw2.ability.*;
import de.ralfhergert.gw2.model.effect.FuryBuff;
import de.ralfhergert.gw2.model.effect.MightBuff;
import de.ralfhergert.generic.function.TriggerableGroup;
import de.ralfhergert.gw2.model.effect.ShockingAura;
import de.ralfhergert.gw2.model.effect.VigorBuff;
import de.ralfhergert.gw2.modifier.CharacterAttributeModifier;

import java.time.Duration;

public enum Rune {
    Fireworks("Superior Rune of Fireworks",
        new CharacterAttributeModifier(CharacterAttribute.Power, 25),
        new CharacterAttributeModifier(CharacterAttribute.BoonDuration, 0.05),
        new CharacterAttributeModifier(CharacterAttribute.Power, 50),
        new CharacterAttributeModifier(CharacterAttribute.BoonDuration, 0.1),
        new CharacterAttributeModifier(CharacterAttribute.Power, 100),
        new AssignableGroup<>(
            new CharacterAttributeModifier(CharacterAttribute.MovementSpeed, new ScaleOperation(1.25)),
            new WhenInCombatTrigger(Duration.ofSeconds(20), new TriggerableGroup<>(
                new ApplyAssignableToSelf((gw2Character) -> new MightBuff(6, Duration.ofSeconds(6), gw2Character)),
                new ApplyAssignableToSelf((gw2Character) -> new FuryBuff(1, Duration.ofSeconds(6), gw2Character)),
                new ApplyAssignableToSelf((gw2Character) -> new VigorBuff(1, Duration.ofSeconds(6), gw2Character))
            ))
        )),
    Surging("Superior Rune of Surging",
        new CharacterAttributeModifier(CharacterAttribute.Power, 25),
        new CharacterAttributeModifier(CharacterAttribute.BoonDuration, 0.05),
        new CharacterAttributeModifier(CharacterAttribute.Power, 50),
        new CharacterAttributeModifier(CharacterAttribute.BoonDuration, 0.1),
        new CharacterAttributeModifier(CharacterAttribute.Power, 100),
        new AssignableGroup<>(
            new CharacterAttributeModifier(CharacterAttribute.MovementSpeed, new ScaleOperation(1.25)),
            new AfterUsingEliteSkillTrigger(Duration.ofSeconds(45), new TriggerableGroup<>(
                new ApplyAssignableToSelf((gw2Character) -> new ShockingAura(Duration.ofSeconds(4), gw2Character))
            ))
        )),
    Pack("Superior Rune of the Pack",
        new CharacterAttributeModifier(CharacterAttribute.Power, 25),
        new CharacterAttributeModifier(CharacterAttribute.BoonDuration, 0.05),
        new CharacterAttributeModifier(CharacterAttribute.Power, 50),
        new CharacterAttributeModifier(CharacterAttribute.BoonDuration, 0.1),
        new CharacterAttributeModifier(CharacterAttribute.Power, 100),
        new AssignableGroup<>(
            new CharacterAttributeModifier(CharacterAttribute.Precision, new IncreaseByInt(125)),
            new WhenInCombatTrigger(Duration.ofSeconds(30), new GrantNearbyAllies(5, 600, true, new TargetedTriggerableGroup<>(
                new ApplyAssignableToTarget((gw2Character) -> new MightBuff(5, Duration.ofSeconds(8), gw2Character)),
                new ApplyAssignableToTarget((gw2Character) -> new FuryBuff(1, Duration.ofSeconds(8), gw2Character)),
                new ApplyAssignableToTarget((gw2Character) -> new VigorBuff(1, Duration.ofSeconds(8), gw2Character))
            )))
        ));

    private final String name;
    private final Assignable<Gw2Character>[] bonus;

    Rune(String name, Assignable<Gw2Character>... bonus) {
        this.name = name;
        this.bonus = bonus;
    }

    public String getName() {
        return name;
    }

    public Assignable<Gw2Character>[] getBonus() {
        return bonus;
    }
}
