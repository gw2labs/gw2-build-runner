package de.ralfhergert.gw2.api.v2;

/**
 * A fact specifying a recharge rate.
 */
public class Gw2FactRecharge extends Gw2Fact {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
