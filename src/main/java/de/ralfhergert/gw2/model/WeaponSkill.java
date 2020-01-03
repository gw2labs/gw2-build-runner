package de.ralfhergert.gw2.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class models what weapons a profession is able to use.
 */
public class WeaponSkill extends Skill {

    private final Profession profession;
    private final WeaponType weaponType;
    private final List<Requirement> equipmentRequirements = new ArrayList<>();
    private final List<Timing> timings = new ArrayList<>();

    public WeaponSkill(int id, String name, Profession profession, WeaponType weaponType) {
        super(id, name);
        this.profession = profession;
        this.weaponType = weaponType;
    }

    public WeaponSkill withRequirements(Requirement... requirements) {
        equipmentRequirements.addAll(Arrays.asList(requirements));
        return this;
    }

    public WeaponSkill withTimings(Timing... timings) {
        this.timings.addAll(Arrays.asList(timings));
        return this;
    }
}
