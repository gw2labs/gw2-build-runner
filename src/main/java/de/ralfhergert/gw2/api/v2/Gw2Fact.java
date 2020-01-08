package de.ralfhergert.gw2.api.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.net.URL;

/**
 * General class for facts. Different fact types do exist. Each of them has a dedicated class.
 */
@JsonSubTypes({
    @JsonSubTypes.Type(value = Gw2FactAttributeAdjust.class, name = "AttributeAdjust"),
    @JsonSubTypes.Type(value = Gw2FactBuff.class, name = "Buff"),
    @JsonSubTypes.Type(value = Gw2FactComboField.class, name = "ComboField"),
    @JsonSubTypes.Type(value = Gw2FactComboFinisher.class, name = "ComboFinisher"),
    @JsonSubTypes.Type(value = Gw2FactDamage.class, name = "Damage"),
    @JsonSubTypes.Type(value = Gw2FactDistance.class, name = "Distance"),
    @JsonSubTypes.Type(value = Gw2FactNoData.class, name = "NoData"),
    @JsonSubTypes.Type(value = Gw2FactNumber.class, name = "Number"),
    @JsonSubTypes.Type(value = Gw2FactPercent.class, name = "Percent"),
    @JsonSubTypes.Type(value = Gw2FactPrefixedBuff.class, name = "PrefixedBuff"),
    @JsonSubTypes.Type(value = Gw2FactRange.class, name = "Range"),
    @JsonSubTypes.Type(value = Gw2FactRecharge.class, name = "Recharge"),
    @JsonSubTypes.Type(value = Gw2FactStunBreak.class, name = "StunBreak"),
    @JsonSubTypes.Type(value = Gw2FactTime.class, name = "Time"),
    @JsonSubTypes.Type(value = Gw2FactUnblockable.class, name = "Unblockable"),
    @JsonSubTypes.Type(value = Gw2FactUnknown.class, name = "")
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", defaultImpl = Gw2FactUnknown.class)
public class Gw2Fact {

    private String text;
    private Gw2FactType type;
    private URL icon;
    private int requiresTrait;
    private int overrides;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Gw2FactType getType() {
        return type;
    }

    public void setType(Gw2FactType type) {
        this.type = type;
    }

    public URL getIcon() {
        return icon;
    }

    public void setIcon(URL icon) {
        this.icon = icon;
    }

    @JsonProperty("requires_trait")
    public int getRequiresTrait() {
        return requiresTrait;
    }

    public void setRequiresTrait(int requiresTrait) {
        this.requiresTrait = requiresTrait;
    }

    public int getOverrides() {
        return overrides;
    }

    public void setOverrides(int overrides) {
        this.overrides = overrides;
    }

}
