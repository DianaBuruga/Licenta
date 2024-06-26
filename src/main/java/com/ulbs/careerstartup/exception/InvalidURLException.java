package com.ulbs.careerstartup.exception;

import java.net.MalformedURLException;

public class InvalidURLException extends RuntimeException {
    public InvalidURLException(String message, MalformedURLException exception) {
        super(message, exception);
    }
}
