package com.ulbs.careerstartup.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.UnexpectedTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice(basePackages = "com.ulbs.careerstartup.controller", annotations = RestController.class)
@Slf4j
public class AppExceptionHandler {

    @ExceptionHandler({OAuth2AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleUnauthorizedExceptions(OAuth2AuthenticationException ex) {
        log.error("Authentication error occurred: {}", ex.getMessage());
        return new ErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler({SecurityException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleForbiddenExceptions(SecurityException ex) {
        log.error("Access forbidden error occurred: {}", ex.getMessage());
        return new ErrorResponse(ex.getMessage(), HttpStatus.FORBIDDEN.value());
    }

    @ExceptionHandler({EntityNotFoundException.class, DefaultImageLoadingException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundExceptions(Exception ex) {
        log.error("Resource not found: {}", ex.getMessage());
        return new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler({UnexpectedTypeException.class,IOException.class, IllegalArgumentException.class, SingleRequestParamException.class, SQLIntegrityConstraintViolationException.class, DataIntegrityViolationException.class, IllegalStateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestExceptions(Exception ex) {
        log.error("Bad request: {}", ex.getMessage());
        return new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
}