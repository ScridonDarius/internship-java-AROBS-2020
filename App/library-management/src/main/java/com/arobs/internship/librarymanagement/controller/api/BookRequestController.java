package com.arobs.internship.librarymanagement.controller.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/bookRequest", produces = {MediaType.APPLICATION_JSON_VALUE})
public class BookRequestController {
}
