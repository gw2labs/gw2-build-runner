package de.ralfhergert.gw2.api.v2;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents a fact specifying a buff.
 */
public class Gw2FactBuff extends Gw2Fact {

    private Gw2BuffConditionType status;
    private int duration;
    private int applyCount;
    private String description;

    public Gw2BuffConditionType getStatus() {
        return status;
    }

    public void setStatus(Gw2BuffConditionType status) {
        this.status = status;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getApplyCount() {
        return applyCount;
    }

    @JsonProperty("apply_count")
    public void setApplyCount(int applyCount) {
        this.applyCount = applyCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
