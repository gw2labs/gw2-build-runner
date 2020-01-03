package de.ralfhergert.gw2.model.requirement;

import de.ralfhergert.gw2.model.Requirement;
import de.ralfhergert.gw2.model.WeaponType;
import de.ralfhergert.gw2.model.WeaponFlag;

public class WeaponEquipped extends Requirement {

    private final WeaponType weaponType;
    private final WeaponFlag equippedAs;

    public WeaponEquipped(WeaponType weaponType, WeaponFlag equippedAs) {
        this.weaponType = weaponType;
        this.equippedAs = equippedAs;
    }
}
