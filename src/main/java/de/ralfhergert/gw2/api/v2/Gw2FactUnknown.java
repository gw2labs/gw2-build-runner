package de.ralfhergert.gw2.api.v2;

/**
 * This fact was created as a workaround for a fact in the trait
 * https://api.guildwars2.com/v2/traits/2123?lang=en
 * there {@code /skills[0]/facts[11]} named "Life Force Cost" has no fact-type.
 */
public class Gw2FactUnknown extends Gw2Fact {

    private int percent;

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
