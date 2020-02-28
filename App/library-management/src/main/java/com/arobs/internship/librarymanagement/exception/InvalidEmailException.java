package com.arobs.internship.librarymanagement.exception;

import io.swagger.models.auth.In;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Invalid email format")
public class InvalidEmailException extends Exception {

}
