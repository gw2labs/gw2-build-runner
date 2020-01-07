package de.ralfhergert.gw2.model.build.v1;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfigurationBlockParserTest {

    @Test
    public void testParsingNull() {
        Stream<ConfigurationBlock> blocks = new ConfigurationBlockParser().apply(null);
        Assert.assertNotNull("stream should not be null", blocks);
        Assert.assertEquals("number of elements on stream", 0, blocks.count());
    }

    @Test
    public void testParsingEmpty() {
        Stream<ConfigurationBlock> blocks = new ConfigurationBlockParser().apply("");
        Assert.assertNotNull("stream should not be null", blocks);
        Assert.assertEquals("number of elements on stream", 0, blocks.count());
    }

    @Test
    public void testParsingSimple01() {
        List<ConfigurationBlock> blocks = new ConfigurationBlockParser().apply("a:foo").collect(Collectors.toList());
        Assert.assertNotNull("blocks should not be null", blocks);
        Assert.assertEquals("blocks should be", Collections.singletonList(new ConfigurationBlock("a", "foo")), blocks);
    }

    @Test
    public void testParsingSimple02() {
        List<ConfigurationBlock> blocks = new ConfigurationBlockParser().apply("a:b:foo").collect(Collectors.toList());
        Assert.assertNotNull("blocks should not be null", blocks);
        Assert.assertEquals("blocks should be", Collections.singletonList(new ConfigurationBlock("a:b", "foo")), blocks);
    }

    @Test
    public void testParsingSimple03() {
        List<ConfigurationBlock> blocks = new ConfigurationBlockParser().apply("a:b:c:foo").collect(Collectors.toList());
        Assert.assertNotNull("blocks should not be null", blocks);
        Assert.assertEquals("blocks should be", Collections.singletonList(new ConfigurationBlock("a:b:c", "foo")), blocks);
    }

    @Test
    public void testParsingFirstLevelConcatenated() {
        List<ConfigurationBlock> blocks = new ConfigurationBlockParser().apply("a:foo;bar").collect(Collectors.toList());
        Assert.assertNotNull("blocks should not be null", blocks);
        Assert.assertEquals("blocks should be", Arrays.asList(
            new ConfigurationBlock("a", "foo"),
            new ConfigurationBlock("a", "bar")
        ), blocks);
    }

    @Test
    public void testParsingSecondLevelConcatenated() {
        List<ConfigurationBlock> blocks = new ConfigurationBlockParser().apply("a:b:foo;c:bar").collect(Collectors.toList());
        Assert.assertNotNull("blocks should not be null", blocks);
        Assert.assertEquals("blocks should be", Arrays.asList(
            new ConfigurationBlock("a:b", "foo"),
            new ConfigurationBlock("a:c", "bar")
        ), blocks);
    }

    @Test
    public void testParsingMixedLevelConcatenated() {
        List<ConfigurationBlock> blocks = new ConfigurationBlockParser().apply("a:b:foo;;c:bar").collect(Collectors.toList());
        Assert.assertNotNull("blocks should not be null", blocks);
        Assert.assertEquals("blocks should be", Arrays.asList(
            new ConfigurationBlock("a:b", "foo"),
            new ConfigurationBlock("c", "bar")
        ), blocks);
    }
}
