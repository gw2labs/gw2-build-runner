package de.ralfhergert.gw2.api.v2;

/**
 * A fact containing a percentage number.
 */
public class Gw2FactPercent extends Gw2Fact {

    private int percent;

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
