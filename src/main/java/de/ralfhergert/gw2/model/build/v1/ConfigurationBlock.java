package de.ralfhergert.gw2.model.build.v1;

import java.util.Objects;

public class ConfigurationBlock {

    private final String identifier;
    private final String content;

    public ConfigurationBlock(String text) {
        final int identifierIndex = text.indexOf(':');
        if (identifierIndex < 0) {
            throw new IllegalArgumentException("block text has no identifier");
        }
        identifier = text.substring(0, identifierIndex);
        content = text.substring(identifierIndex + 1);
    }

    public ConfigurationBlock(String identifier, String content) {
        this.identifier = identifier;
        this.content = content;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConfigurationBlock that = (ConfigurationBlock)o;
        return Objects.equals(identifier, that.identifier) &&
            Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, content);
    }

    @Override
    public String toString() {
        return "ConfigurationBlock{" +
            "identifier='" + identifier + '\'' +
            ", content='" + content + '\'' +
            '}';
    }
}
