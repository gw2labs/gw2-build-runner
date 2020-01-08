package de.ralfhergert.gw2.api.v2;

/**
 * A fact specifying a range.
 */
public class Gw2FactRange extends Gw2Fact {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
