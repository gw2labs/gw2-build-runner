package de.ralfhergert.generic.function;

/**
 * Interface to implement triggerable actions as lambdas.
 */
@FunctionalInterface
public interface TargetedTriggerable<Source,Target> {

    void trigger(Source source, Target target);
}
