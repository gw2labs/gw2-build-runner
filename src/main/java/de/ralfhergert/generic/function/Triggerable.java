package de.ralfhergert.generic.function;

/**
 * Interface to implement triggerable actions as lambdas.
 */
@FunctionalInterface
public interface Triggerable {

    void trigger();
}
