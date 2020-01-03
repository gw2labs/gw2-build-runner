package de.ralfhergert.generic.number;

import java.util.Objects;
import java.util.function.Function;

public abstract class IncreaseOperation implements Function<Number,Number> {

    protected final Number increase;

    public IncreaseOperation(Number increase) {
        this.increase = increase;
    }

    public Number getIncrease() {
        return increase;
    }

    @Override
    public abstract Number apply(Number number);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IncreaseOperation that = (IncreaseOperation) o;
        return Objects.equals(increase, that.increase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(increase);
    }

    @Override
    public String toString() {
        return "IncreaseOperation{" +
            "increase=" + increase +
            '}';
    }
}
