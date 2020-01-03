package de.ralfhergert.gw2.model.requirement;

import de.ralfhergert.gw2.model.Requirement;
import de.ralfhergert.gw2.model.Specialization;

public class SpecializationEquipped extends Requirement {

    private final Specialization specialization;

    public SpecializationEquipped(Specialization specialization) {
        this.specialization = specialization;
    }
}
