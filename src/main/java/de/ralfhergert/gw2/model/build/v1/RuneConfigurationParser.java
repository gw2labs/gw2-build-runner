package de.ralfhergert.gw2.model.build.v1;

import de.ralfhergert.generic.util.CamelCaseMatcher;
import de.ralfhergert.gw2.model.Rune;
import de.ralfhergert.gw2.model.RuneSlot;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RuneConfigurationParser implements Function<String,RuneConfiguration> {

    @Override
    public RuneConfiguration apply(String text) {
        if (text == null) {
            throw new IllegalArgumentException("configuration text must not be null");
        }
        final List<RuneSlot> slots;
        final String[] fragments = text.split("=");

        if (fragments.length > 1 && !fragments[0].isEmpty() && !fragments[0].equals("*")) {
            slots = Arrays.stream(fragments[0].split(","))
                .flatMap(runeDef -> Arrays.stream(RuneSlot.values())
                    .filter(new CamelCaseMatcher<>(runeDef, RuneSlot::name))
                )
                .collect(Collectors.toList());
        } else {
            slots = Arrays.asList(RuneSlot.values());
        }

        final String runeDef = fragments.length > 1 ? fragments[1] : fragments[0];
        final List<Rune> foundRunes = Arrays.stream(Rune.values())
            .filter(new CamelCaseMatcher<>(runeDef, Rune::name))
            .collect(Collectors.toList());

        if (foundRunes.isEmpty()) {
            throw new IllegalArgumentException("Could not find the appropriate rune for '" + runeDef + "'");
        } else if (foundRunes.size() > 1) {
            throw new IllegalArgumentException("multiple prefixes " + foundRunes.toString() + " found for '" + runeDef + "'. Please specify more accurately.");
        } else {
            return new RuneConfiguration(slots, foundRunes.get(0));
        }
    }
}
