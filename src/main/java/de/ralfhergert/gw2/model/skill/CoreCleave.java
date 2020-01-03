package de.ralfhergert.gw2.model.skill;

import de.ralfhergert.gw2.model.*;
import de.ralfhergert.gw2.model.requirement.SpecializationEquipped;
import de.ralfhergert.gw2.model.requirement.WeaponEquipped;
import de.ralfhergert.gw2.model.timing.Channelling;

import java.time.Duration;

/**
 * Cleave at your enemy with a physical and a magical axe.
 *
 * Timings as observed in https://www.youtube.com/watch?v=b2Bp3OGV76s:
 *    0ms (0:00.80) skill is triggered and starts channeling
 *    | channeling with movement possible
 *  500ms (0:01.37) skill hits with 1 direct hit (0.36) and puts 1 stack of bleeding (1s) onto the target
 *        (0:01.56) skill hits with 1 direct hit (0.36) and puts 1 stack of bleeding (1s) onto the target
 *
 */
public class CoreCleave extends WeaponSkill {

    public CoreCleave() {
        super(45047, "Core Cleave", Profession.Guardian, WeaponType.Axe);
        withRequirements(
            new SpecializationEquipped(Specialization.Firebrand),
            new WeaponEquipped(WeaponType.Axe, WeaponFlag.Mainhand)
        );
        withTimings(
            new Channelling(Duration.ofMillis(500))
        );
    }
}
