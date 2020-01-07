package de.ralfhergert.generic.util;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This CamelCase matcher allows to find enums by using a shorter version of the name.
 * For instance an enum with the name "CamelCase" will be matched by searching for:
 *  - "Camel"
 *  - "CamCa" or even
 *  - "CC"
 *
 * @param <Type> Type of object this matcher should operate on.
 */
public class CamelCaseMatcher<Type> implements Predicate<Type> {

    private final List<String> nameToSearchFor;
    private final Function<Type,String> nameExtractor;

    public CamelCaseMatcher(String nameToSearchFor, Function<Type,String> nameExtractor) {
        this.nameToSearchFor = splitIntoWords(nameToSearchFor).collect(Collectors.toList());
        this.nameExtractor = nameExtractor;
    }

    public CamelCaseMatcher(String nameToSearchFor) {
        this(nameToSearchFor, String::valueOf);
    }

    public static Stream<String> splitIntoWords(String name) {
        if (name == null || name.isEmpty()) {
            return Stream.empty();
        } else {
            // find the next upper case letter
            for (int i = 1; i < name.length(); i++) {
                final char chr = name.charAt(i);
                if ('A' <= chr && chr <= 'Z') {
                    return Stream.concat(Stream.of(name.substring(0, i)), splitIntoWords(name.substring(i)));
                }
            }
            // if this point is reach the name did not contain any other upper case letters.
            return Stream.of(name);
        }
    }

    @Override
    public boolean test(Type type) {
        final List<String> enumName = splitIntoWords(nameExtractor.apply(type)).collect(Collectors.toList());
        if (nameToSearchFor.size() <= enumName.size()) {
            for (int i = 0; i < nameToSearchFor.size(); i++) {
                if (!enumName.get(i).startsWith(nameToSearchFor.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
