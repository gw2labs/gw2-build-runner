package de.ralfhergert.gw2.model.build.v1;

import de.ralfhergert.generic.util.CamelCaseMatcher;
import de.ralfhergert.gw2.model.Prefix;
import de.ralfhergert.gw2.model.TrinketSlot;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TrinketConfigurationParser implements Function<String,TrinketConfiguration> {

    @Override
    public TrinketConfiguration apply(String text) {
        final List<TrinketSlot> slots;
        final String[] fragments = text.split("=");

        if (fragments.length > 1 && !fragments[0].isEmpty() && !fragments[0].equals("*")) {
            slots = Arrays.stream(fragments[0].split(","))
                .flatMap(prefix -> Arrays.stream(TrinketSlot.values())
                    .filter(new CamelCaseMatcher<>(prefix, TrinketSlot::name))
                )
                .collect(Collectors.toList());
        } else {
            slots = Arrays.asList(TrinketSlot.values());
        }

        final String prefixDef = fragments.length > 1 ? fragments[1] : fragments[0];
        final List<Prefix> foundPrefixes = Arrays.stream(Prefix.values())
            .filter(new CamelCaseMatcher<>(prefixDef, Prefix::name))
            .collect(Collectors.toList());

        if (foundPrefixes.isEmpty()) {
            throw new IllegalArgumentException("Could not find the appropriate prefix for '" + prefixDef + "'");
        } else if (foundPrefixes.size() > 1) {
            throw new IllegalArgumentException("multiple prefixes " + foundPrefixes.toString() + " found for '" + prefixDef + "'. Please specify more accurately.");
        } else {
            return new TrinketConfiguration(slots, foundPrefixes.get(0));
        }
    }
}
