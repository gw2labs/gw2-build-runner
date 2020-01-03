package de.ralfhergert.gw2.api.v2;

import java.net.URL;

public class Gw2Prefix {

    private String text;
    private URL icon;
    private String status;
    private String description;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public URL getIcon() {
        return icon;
    }

    public void setIcon(URL icon) {
        this.icon = icon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
