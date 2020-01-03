package de.ralfhergert.generic.number;

public class IncreaseByDouble extends IncreaseOperation {

    public IncreaseByDouble(Number increase) {
        super(increase);
    }

    @Override
    public Number apply(Number a) {
        return a.doubleValue() + increase.doubleValue();
    }

    @Override
    public String toString() {
        return "IncreaseByDouble{increase=" + increase + "}";
    }
}
