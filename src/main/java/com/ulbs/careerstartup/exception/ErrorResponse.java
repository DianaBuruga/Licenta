package com.ulbs.careerstartup.exception;

import java.time.Instant;

import static com.ulbs.careerstartup.util.DateFormatter.formatDate;

public class ErrorResponse {
    private final String message;
    private final int code;
    private final String dateTime;

    public ErrorResponse(String message, int code) {
        this.message = message;
        this.code = code;
        this.dateTime = formatDate(Instant.now());
    }
}
