package com.arobs.internship.librarymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidEmailException extends Exception {

    private HttpStatus status;
    private String message;

    public InvalidEmailException(HttpStatus status, String message) {
       this.status = status;
       this.message = message;
    }
}
