package de.ralfhergert.gw2.api.v2;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Enumeration specifying different fact types used by Guild Wars 2.
 */
public enum Gw2FactType {
    AttributeAdjust,
    Buff,
    Number,
    Recharge,
    @JsonProperty("") Unknown
}
