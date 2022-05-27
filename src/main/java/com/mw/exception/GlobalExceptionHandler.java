package com.mw.exception;

import com.mw.exception.custom.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleAccountAlreadyExistException(AccountAlreadyExistException e) {
        ErrorResponse response = ErrorResponse.of(e.getMessage());
        return getResponse(response, HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAccountNotFoundException(AccountNotFoundException e) {
        ErrorResponse response = ErrorResponse.of(e.getMessage());
        return getResponse(response, HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(CodeNotEqualException.class)
    public ResponseEntity<ErrorResponse> handleCodeNotEqualException(CodeNotEqualException e) {
        ErrorResponse response = ErrorResponse.of(e.getMessage());
        return getResponse(response, HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(NotInEmailFormatException.class)
    public ResponseEntity<ErrorResponse> handleNotInEmailFormatException(NotInEmailFormatException e) {
        ErrorResponse response = ErrorResponse.of(e.getMessage());
        return getResponse(response, HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(NotMyoungJiMemberException.class)
    public ResponseEntity<ErrorResponse> handleNotMyoungJiMemberException(NotMyoungJiMemberException e) {
        ErrorResponse response = ErrorResponse.of(e.getMessage());
        return getResponse(response, HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(PasswordIncorrectException.class)
    public ResponseEntity<ErrorResponse> handlePasswordIncorrectException(PasswordIncorrectException e) {
        ErrorResponse response = ErrorResponse.of(e.getMessage());
        return getResponse(response, HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleObjectNotFoundException(ObjectNotFoundException e) {
        ErrorResponse response = ErrorResponse.of(e.getMessage());
        return getResponse(response, HttpStatus.BAD_REQUEST.value());
    }

    private ResponseEntity<ErrorResponse> getResponse(ErrorResponse response, Integer statusCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        return new ResponseEntity(response, headers, statusCode);
    }
}
