package com.arobs.internship.librarymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND)
public class FoundException extends Exception {

    public FoundException(String message) {
        super(message);
    }

}
