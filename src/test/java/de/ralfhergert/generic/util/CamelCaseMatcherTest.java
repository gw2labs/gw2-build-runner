package de.ralfhergert.generic.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CamelCaseMatcherTest {

    @Test
    public void testSplitIntoWords01() {
        final List<String> words = CamelCaseMatcher.splitIntoWords("CamelCase").collect(Collectors.toList());
        Assert.assertNotNull("words should not  be null", words);
        Assert.assertEquals("words should be", Arrays.asList("Camel", "Case"), words);
    }

    @Test
    public void testSplitIntoWords02() {
        final List<String> words = CamelCaseMatcher.splitIntoWords("Camel").collect(Collectors.toList());
        Assert.assertNotNull("words should not  be null", words);
        Assert.assertEquals("words should be", Collections.singletonList("Camel"), words);
    }

    @Test
    public void testMatching01() {
        final List<String> foundStrings = Stream.of("CamelCase", "CamelMaze", "CamelDaze")
            .filter(new CamelCaseMatcher<>("CC", Function.identity()))
            .collect(Collectors.toList());
        Assert.assertNotNull("foundStrings should not  be null", foundStrings);
        Assert.assertEquals("foundStrings should be", Collections.singletonList("CamelCase"), foundStrings);
    }

    @Test
    public void testMatching02() {
        final List<String> foundStrings = Stream.of("CamelCase", "CamelMaze", "CamelDaze")
            .filter(new CamelCaseMatcher<>("Camel"))
            .collect(Collectors.toList());
        Assert.assertNotNull("foundStrings should not  be null", foundStrings);
        Assert.assertEquals("foundStrings should be", Arrays.asList("CamelCase", "CamelMaze", "CamelDaze"), foundStrings);
    }
}
