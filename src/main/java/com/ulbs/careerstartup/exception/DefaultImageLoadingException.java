package com.ulbs.careerstartup.exception;

import java.io.IOException;

public class DefaultImageLoadingException extends RuntimeException {
    public DefaultImageLoadingException(String message, IOException e) {
        super(message, e);
    }
}
