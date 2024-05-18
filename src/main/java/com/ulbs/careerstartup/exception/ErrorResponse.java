package com.ulbs.careerstartup.exception;

import java.time.Instant;

import static com.ulbs.careerstartup.util.DateFormatter.formatDate;

public class ErrorResponse {
    private String message;
    private int code;
    private String dateTime;

    public ErrorResponse(String message, int code) {
        this.message = message;
        this.code = code;
        this.dateTime = formatDate(Instant.now());
    }
}
