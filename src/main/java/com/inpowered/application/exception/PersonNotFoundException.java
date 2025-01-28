package com.inpowered.application.exception;

public class PersonNotFoundException  extends RuntimeException {
    public PersonNotFoundException(String message) {
        super(message);
    }
}
