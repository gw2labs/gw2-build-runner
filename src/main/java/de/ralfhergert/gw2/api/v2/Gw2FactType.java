package de.ralfhergert.gw2.api.v2;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Gw2FactType {
    AttributeAdjust,
    Buff,
    Number,
    Recharge,
    @JsonProperty("") Unknown
}
