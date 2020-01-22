package de.ralfhergert.gw2.model;

import de.ralfhergert.generic.math.Vector3L;

public class WorldPosition {

    private final Vector3L position;
    private final Vector3L heading;

    public WorldPosition(Vector3L position, Vector3L heading) {
        this.position = position;
        this.heading = heading;
    }

    public Vector3L getPosition() {
        return position;
    }

    public Vector3L getHeading() {
        return heading;
    }
}
