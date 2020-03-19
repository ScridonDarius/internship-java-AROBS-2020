package com.arobs.internship.librarymanagement.controller.api;

import com.arobs.internship.librarymanagement.model.enums.RentRequestConfirmationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rentRequestConfirmation", produces = {MediaType.APPLICATION_JSON_VALUE})
public class RentRequestConfirmationController {

    @RequestMapping(value = "/confirmation", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RentRequestConfirmationResponse> confirmation(@RequestParam RentRequestConfirmationResponse rentRequestConfirmation) {
        return new ResponseEntity<>(rentRequestConfirmation, HttpStatus.OK);
    }
}
