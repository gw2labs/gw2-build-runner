package de.ralfhergert.gw2.model.build.v1;

import de.ralfhergert.gw2.model.ArmorSlot;
import de.ralfhergert.gw2.model.Prefix;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ArmorConfigurationParser implements Function<String,ArmorConfiguration> {

    @Override
    public ArmorConfiguration apply(String text) {
        if (text == null) {
            throw new IllegalArgumentException("configuration must not be null");
        }
        final List<ArmorSlot> slots;
        final String[] fragments = text.split("=");

        if (fragments.length > 1 && !fragments[0].isEmpty() && !fragments[0].equals("*")) {
            slots = Arrays.stream(fragments[0].split(","))
                .flatMap(prefix -> Arrays.stream(ArmorSlot.values())
                    .filter(armorSlot -> armorSlot.name().startsWith(prefix))
                )
                .collect(Collectors.toList());
        } else {
            slots = Arrays.asList(ArmorSlot.values());
        }
        final String prefixDef = fragments.length > 1 ? fragments[1] : fragments[0];
        final Predicate<Prefix> prefixFilter;
        if (prefixDef.startsWith("n'")) { // search for the Armor name, e.g. 'Zojja'
            final String prefixByName = prefixDef.substring(2);
            prefixFilter = prefix -> prefix.getArmorName().startsWith(prefixByName);
        } else if (prefixDef.startsWith("p'")) { // search for the prefix name, e.g. 'Berserker'
            final String prefixByName = prefixDef.substring(2);
            prefixFilter = prefix -> prefix.name().startsWith(prefixByName);
        } else { // search for prefix name and armor name.
            prefixFilter = prefix -> prefix.name().startsWith(prefixDef) || prefix.getArmorName().startsWith(prefixDef);
        }
        List<Prefix> foundPrefixes = Arrays.stream(Prefix.values()).filter(prefixFilter).collect(Collectors.toList());

        if (foundPrefixes.isEmpty()) {
            throw new IllegalArgumentException("Could not find the appropriate prefix for '" + prefixDef + "'");
        } else if (foundPrefixes.size() > 1) {
            throw new IllegalArgumentException("multiple prefixes " + foundPrefixes.toString() + " found for '" + prefixDef + "'. Please specify more accurately.");
        } else {
            return new ArmorConfiguration(slots, foundPrefixes.get(0));
        }
    }
}
