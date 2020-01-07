package de.ralfhergert.gw2.modifier;

import de.ralfhergert.generic.cloning.Copyable;
import de.ralfhergert.generic.number.IncreaseByDouble;
import de.ralfhergert.generic.number.IncreaseByInt;
import de.ralfhergert.generic.number.IncreaseOperation;
import de.ralfhergert.generic.value.ValueModifier;
import de.ralfhergert.gw2.model.CharacterAttribute;
import de.ralfhergert.gw2.model.Gw2Character;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * This is a generic modifier for a single {@link CharacterAttribute}.
 */
public class CharacterAttributeModifier extends ValueModifier<Number,Gw2Character> implements Copyable<CharacterAttributeModifier> {

    private final CharacterAttribute key;
    private final Function<Number,Number> operation;

    public CharacterAttributeModifier(Object source, CharacterAttribute key, Function<Number,Number> operation) {
        super(source);
        this.key = key;
        this.operation = operation;
    }

    public CharacterAttributeModifier(CharacterAttribute key, Function<Number,Number> operation) {
        this(null, key, operation);
    }

    public CharacterAttributeModifier(CharacterAttribute key, int increase) {
        this(null, key, new IncreaseByInt(increase));
    }

    public CharacterAttributeModifier(CharacterAttribute key, double increase) {
        this(null, key, new IncreaseByDouble(increase));
    }

    @Override
    public CharacterAttributeModifier deepCopy() {
        return new CharacterAttributeModifier(getSource(), key, operation);
    }

    @Override
    public void assignTo(Gw2Character gw2Character) {
        gw2Character.getAttribute(key).addModifier(this);
    }

    @Override
    public void resignFrom(Gw2Character gw2Character) {
        gw2Character.getAttribute(key).removeModifier(this);
    }

    @Override
    public Number modify(Number value, Gw2Character gw2Character) {
        return operation.apply(value);
    }

    public CharacterAttribute getKey() {
        return key;
    }

    public Function<Number,Number> getOperation() {
        return operation;
    }

    /**
     * This is a convenience method, for the case the operation is a increase operation.
     */
    public Optional<Number> getIncrease() {
        return operation instanceof IncreaseOperation
            ? Optional.of(((IncreaseOperation)operation).getIncrease())
            : Optional.empty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CharacterAttributeModifier that = (CharacterAttributeModifier)o;
        return Objects.equals(operation, that.operation) &&
            key == that.key;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, operation);
    }

    @Override
    public String toString() {
        return "CharacterAttributeModifier{" +
            "key=" + key +
            ", operation=" + operation +
            '}';
    }

    public static class AddDouble implements BiFunction<Number,Number,Number> {
        @Override
        public Number apply(Number a, Number b) {
            return a.doubleValue() + b.doubleValue();
        }
    }
}
