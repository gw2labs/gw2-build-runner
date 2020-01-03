package de.ralfhergert.gw2.api.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.ralfhergert.gw2.model.ComboFinisherType;

public class Gw2FactComboFinisher extends Gw2Fact {

    private ComboFinisherType finisherType;
    private int percent;

    @JsonProperty("finisher_type")
    public ComboFinisherType getFinisherType() {
        return finisherType;
    }

    public void setFinisherType(ComboFinisherType finisherType) {
        this.finisherType = finisherType;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
