package de.ralfhergert.generic.number;

/**
 * This operation increases a double number by a fix double increase value.
 */
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
