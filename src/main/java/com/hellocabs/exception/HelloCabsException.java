package com.hellocabs.exception;

public class HelloCabsException extends RuntimeException {
    public HelloCabsException() {

    }
    public HelloCabsException(String message) {
        super(message);
    }

    public HelloCabsException(String message, Throwable clause) {
        super(message,clause);
    }
}
