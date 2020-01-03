package de.ralfhergert.gw2.model;

import de.ralfhergert.generic.number.ScaleOperation;
import de.ralfhergert.generic.value.Assignable;
import de.ralfhergert.generic.value.AssignableGroup;
import de.ralfhergert.gw2.ability.WhenInCombatTrigger;
import de.ralfhergert.gw2.model.effect.FuryBuff;
import de.ralfhergert.gw2.model.effect.MightBuff;
import de.ralfhergert.generic.function.TriggerableGroup;
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
            new WhenInCombatTrigger(Duration.ofSeconds(20), new TriggerableGroup(
                () -> new MightBuff(6, Duration.ofSeconds(6)),
                () -> new FuryBuff(1, Duration.ofSeconds(6)),
                () -> new VigorBuff(1, Duration.ofSeconds(6))
            ))
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
