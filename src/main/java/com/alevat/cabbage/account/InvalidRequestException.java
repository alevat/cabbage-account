package com.alevat.cabbage.account;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(String message, Throwable e) {
        super(message, e);
    }

    public InvalidRequestException(String message) {
        super(message);
    }
}
