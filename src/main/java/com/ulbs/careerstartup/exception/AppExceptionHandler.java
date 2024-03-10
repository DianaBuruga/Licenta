package com.ulbs.careerstartup.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler({OAuth2AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUnauthorizedExceptions(Exception ex) {
        return ex.getMessage();
    }

    @ExceptionHandler({SecurityException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleForbiddenExceptions(Exception ex) {
        return ex.getMessage();
    }

    @ExceptionHandler({EntityNotFoundException.class, DefaultImageLoadingException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundExceptions(Exception ex) {
        return ex.getMessage();
    }

    @ExceptionHandler({IOException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBadRequestException(Exception ex) {
        return ex.getMessage();
    }
}
