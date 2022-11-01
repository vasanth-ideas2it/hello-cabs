/*
 * <p>
 *   Copyright (c) All rights reserved Ideas2IT
 * </p>
 */
package com.hellocabs.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HelloCabsException extends RuntimeException {

    public HelloCabsException(String message) {
        super(message);
    }
    public HelloCabsException(String message, Throwable clause) {
        super(message,clause);
    }
    public HelloCabsException(Throwable clause) {
        super(clause);
    }
}
