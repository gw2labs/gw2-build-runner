package de.ralfhergert.generic.function;

import de.ralfhergert.gw2.model.Gw2Character;

/**
 * Interface to implement triggerable actions as lambdas.
 */
@FunctionalInterface
public interface Triggerable<Source> {

    void trigger(Source source);
}
