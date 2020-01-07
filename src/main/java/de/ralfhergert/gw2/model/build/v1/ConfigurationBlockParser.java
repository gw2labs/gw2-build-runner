package de.ralfhergert.gw2.model.build.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * A configuration block always starts with an identifier
 *
 */
public class ConfigurationBlockParser implements Function<String,Stream<ConfigurationBlock>> {

    @Override
    public Stream<ConfigurationBlock> apply(String text) {
        return asStream(text, new ArrayList<>())
            .filter(block -> !block.getContent().isEmpty());
    }

    private Stream<ConfigurationBlock> asStream(String text, List<String> identifiers) {
        if (text == null || text.isEmpty()) {
            return Stream.empty();
        }
        final int nextColon = text.indexOf(":");
        final int nextSemicolon = text.indexOf(";");
        if (nextColon < 0 && nextSemicolon < 0) {
            return Stream.of(new ConfigurationBlock(String.join(":", identifiers), text));
        } else if (nextColon >= 0 && nextSemicolon >= 0) {
            if (nextColon < nextSemicolon) {
                identifiers.add(text.substring(0, nextColon));
                return asStream(text.substring(nextColon + 1), identifiers);
            } else {
                final String currentIdentifier = String.join(":", identifiers);
                identifiers.remove(identifiers.size() - 1);
                return Stream.concat(
                    Stream.of(new ConfigurationBlock(currentIdentifier, text.substring(0, nextSemicolon))),
                    asStream(text.substring(nextSemicolon + 1), identifiers)
                );
            }
        } else if (nextColon >= 0) { // there is no trailing semicolon
            identifiers.add(text.substring(0, nextColon));
            return asStream(text.substring(nextColon + 1), identifiers);
        } else { // there is a semicolon
            return Stream.concat(
                Stream.of(new ConfigurationBlock(String.join(":", identifiers), text.substring(0, nextSemicolon))),
                asStream(text.substring(nextSemicolon + 1), identifiers)
            );
        }
    }
}
