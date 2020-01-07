package de.ralfhergert.generic.number;

/**
 * This operation increases a int number by a fix int increase value.
 */
public class IncreaseByInt extends IncreaseOperation {

    public IncreaseByInt(Number increase) {
        super(increase);
    }

    @Override
    public Number apply(Number a) {
        return a.intValue() + increase.intValue();
    }

    @Override
    public String toString() {
        return "IncreaseByInt{increase=" + increase + "}";
    }
}
