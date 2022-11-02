/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.exception;

import lombok.NoArgsConstructor;

/**
 * <p>
 * HelloCabsException is the class of those exceptions that can be thrown
 * during the normal operation of the hello cabs application.
 * </p>
 *
 * @author Divya S
 *
 * @version 1.0 Oct-29-2022
 */
@NoArgsConstructor
public class HelloCabsException extends RuntimeException {
    /**
     * <p>
     * Constructs a new HelloCabs exception with the specified detail message.
     * </p>
     *
     * @param message
     *        the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public HelloCabsException(String message) {
        super(message);
    }

    /**
     * <p>
     * Constructs a new HelloCabs exception with the specified detail message and cause.
     * </p>
     *
     * @param message
     *        the detail message (which is saved for later retrieval by the getMessage() method).
     * @param cause
     *        the cause (which is saved for later retrieval by the getCause() method).
     */
    public HelloCabsException(String message, Throwable cause) {
        super(message,cause);
    }

    /**
     * <p>
     * Constructs a new HelloCabs exception with the throwable cause.
     * </p>
     *
     * @param cause
     *        the cause (which is saved for later retrieval by the getCause() method).
     */
    public HelloCabsException(Throwable cause) {
        super(cause);
    }
}
