package de.ralfhergert.gw2.model;

import java.time.Duration;

public class Skill {

    private final int id;
    private final String name;

    private Duration channelingDuration;
    private Duration cooldownDuration;

    /* Indicates whether this ability is currently accessible. For instance if this is a weapon ability
     * and the weapon is currently swapped-out, then this ability is not accessible and will become accessible
     * if the weapon is swapped-in again. */
    private boolean accessible;

    public Skill(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
