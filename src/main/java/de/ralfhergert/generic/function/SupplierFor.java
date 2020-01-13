package de.ralfhergert.generic.function;

@FunctionalInterface
public interface SupplierFor<Parameter,Result> {

    Result get(Parameter parameter);
}
