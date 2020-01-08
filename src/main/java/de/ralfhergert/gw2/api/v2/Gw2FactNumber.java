package de.ralfhergert.gw2.api.v2;

/**
 * A fact containing a number.
 */
public class Gw2FactNumber extends Gw2Fact {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
