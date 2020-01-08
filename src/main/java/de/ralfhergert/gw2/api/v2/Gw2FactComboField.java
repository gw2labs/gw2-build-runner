package de.ralfhergert.gw2.api.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.ralfhergert.gw2.model.ComboFieldType;

/**
 * A fact specifying that a combo field is created.
 */
public class Gw2FactComboField extends Gw2Fact {

    private ComboFieldType fieldType;

    @JsonProperty("field_type")
    public ComboFieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(ComboFieldType fieldType) {
        this.fieldType = fieldType;
    }
}
