package de.ralfhergert.gw2.api.v2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Gw2FactDamage extends Gw2Fact {

    private int hitCount;
    private double damageMultiplier;

    @JsonProperty("hit_count")
    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    @JsonProperty("dmg_multiplier")
    public double getDamageMultiplier() {
        return damageMultiplier;
    }

    public void setDamageMultiplier(double damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }
}
