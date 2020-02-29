package com.arobs.internship.librarymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class InvalidTagException extends Exception {

    public InvalidTagException(String message) {
        super(message);
    }
}
