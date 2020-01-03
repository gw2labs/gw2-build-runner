package de.ralfhergert.gw2.api.v2;

public class Gw2FactAttributeAdjust extends Gw2Fact {

    private int value;
    private String target;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
