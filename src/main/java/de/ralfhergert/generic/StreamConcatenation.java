package de.ralfhergert.generic;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamConcatenation {

    private StreamConcatenation() {}

    public static <T> Stream<T> of(Stream<T>... streams) {
        if (streams.length == 1) {
            return streams[0];
        } if (streams.length == 2) {
            return Stream.concat(streams[0], streams[1]);
        } else {
            return Stream.concat(streams[0], of(Arrays.copyOfRange(streams, 1, streams.length - 1)));
        }
    }
}
