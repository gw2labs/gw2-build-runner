package de.ralfhergert.gw2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class World {

    private final List<Gw2Character> gw2Characters = new ArrayList<>();

    public World addCharacter(Gw2Character gw2Character) {
        if (!gw2Characters.contains(gw2Character)) {
            gw2Characters.add(gw2Character);
        }
        return this;
    }

    public World removeCharacter(Gw2Character gw2Character) {
        gw2Characters.remove(gw2Character);
        return this;
    }

    public Stream<Gw2Character> findAll(Predicate<Gw2Character> predicate) {
        return gw2Characters.stream().filter(predicate);
    }
}
