package de.ralfhergert.gw2.generator;

import de.ralfhergert.gw2.model.Gw2Character;
import de.ralfhergert.gw2.model.build.v1.BuildId;

import java.util.Objects;

/**
 * This class converts a BuildId given as String into a {@link Gw2Character}.
 * A build Id looks like this:
 * <code>GW2L1-p:Re-</code>
 * It always starts with the literal "GW2L" which reads "Guild Wars 2 Labs". The number following
 * this literal is the schema version number of the buildId. Currently only version 1 exists. The
 * buildId can be split by the "-" dashes into different blocks.
 */
public class BuildIdConverter {

    public static Gw2Character buildWith(String buildId) {
        Objects.requireNonNull("buildId must not be null", buildId);
        if (buildId.startsWith("GW2L1-")) {
            return new BuildId().createFrom(buildId);
        } else {
            throw new IllegalArgumentException("no parser for given buildId found");
        }
    }
}
