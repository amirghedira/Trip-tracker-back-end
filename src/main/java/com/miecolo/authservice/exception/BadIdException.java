package com.miecolo.authservice.exception;

public class BadIdException extends Exception{
    public BadIdException() {
        super();
    }

    public BadIdException(String message) {
        super(message);
    }
}
