package de.ralfhergert.gw2.model.build.v1;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

public class ConfigurationBlockMapper<Result> implements Function<Stream<ConfigurationBlock>,Stream<Result>> {

    private final String blockIdentifier;
    private final Function<String,Result> resultMapper;

    public ConfigurationBlockMapper(String blockIdentifier, Function<String, Result> resultMapper) {
        this.blockIdentifier = Objects.requireNonNull(blockIdentifier, "block identifier");
        this.resultMapper = Objects.requireNonNull(resultMapper, "result mapper");
    }

    @Override
    public Stream<Result> apply(Stream<ConfigurationBlock> blocks) {
        return blocks
            .filter(block -> blockIdentifier.equals(block.getIdentifier()))
            .map(ConfigurationBlock::getContent)
            .map(resultMapper);
    }
}
