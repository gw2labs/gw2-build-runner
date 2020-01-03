package de.ralfhergert.generic;

public class UnexpectedError extends RuntimeException {

    public UnexpectedError(String message) {
        super(message);
    }

    public UnexpectedError(String message, Throwable cause) {
        super(message, cause);
    }
}
