package com.mw.exception;

import java.util.List;

public class ErrorResponse {
    private final String message;
    private final List<Error> errors;

    public ErrorResponse(String message, List<Error> errors) {
        this.message = message;
        this.errors = errors;
    }

    static ErrorResponse of(String message) {
        return of(message, null);
    }

    static ErrorResponse of(String message, List<Error> errors) {
        return new ErrorResponse(message, errors);
    }
}
