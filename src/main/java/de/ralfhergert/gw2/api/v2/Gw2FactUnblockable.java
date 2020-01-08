package de.ralfhergert.gw2.api.v2;

/**
 * A fact describing  that a skill is unblockable.
 */
public class Gw2FactUnblockable extends Gw2Fact {

    private boolean value;

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
