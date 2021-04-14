package com.miecolo.authservice.exception;

public class UsernameOrEmailExistException extends Exception {
    public UsernameOrEmailExistException() {
    }

    public UsernameOrEmailExistException(String message) {
        super(message);
    }
}
