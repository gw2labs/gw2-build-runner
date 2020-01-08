package de.ralfhergert.gw2.api.v2;

/**
 * A fact describing a duration.
 */
public class Gw2FactTime extends Gw2Fact {

    private int duration;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
