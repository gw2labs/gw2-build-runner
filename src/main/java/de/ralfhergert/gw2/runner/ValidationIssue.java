package de.ralfhergert.gw2.runner;

public class ValidationIssue {

    private final String message;

    public ValidationIssue(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ValidationIssue{message='" + message + "\'}";
    }
}
