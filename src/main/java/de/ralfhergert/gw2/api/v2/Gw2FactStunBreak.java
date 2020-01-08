package de.ralfhergert.gw2.api.v2;

/**
 * A fact specifying that a skill is a stun break.
 */
public class Gw2FactStunBreak extends Gw2Fact {

    private boolean value;

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
