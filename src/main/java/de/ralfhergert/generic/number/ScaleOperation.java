package de.ralfhergert.generic.number;

import java.util.Objects;
import java.util.function.Function;

/**
 * This operation scales a number by a given factor.
 */
public class ScaleOperation implements Function<Number,Number> {

    protected final Number factor;

    public ScaleOperation(Number factor) {
        this.factor = factor;
    }

    public Number getFactor() {
        return factor;
    }

    @Override
    public Number apply(Number number) {
        return number.doubleValue() * factor.doubleValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ScaleOperation that = (ScaleOperation)o;
        return Objects.equals(factor, that.factor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(factor);
    }

    @Override
    public String toString() {
        return "ScaleOperation{" +
            "factor=" + factor +
            '}';
    }
}
