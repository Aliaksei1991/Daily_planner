package com.example.dailyplanner.exceptions;

public class NoAuthoriseUserFoundException extends RuntimeException{
    public NoAuthoriseUserFoundException() {
    }

    public NoAuthoriseUserFoundException(String message) {
        super(message);
    }

    public NoAuthoriseUserFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAuthoriseUserFoundException(Throwable cause) {
        super(cause);
    }

    public NoAuthoriseUserFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
