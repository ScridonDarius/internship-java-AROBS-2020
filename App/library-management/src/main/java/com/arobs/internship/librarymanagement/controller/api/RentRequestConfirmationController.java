package com.arobs.internship.librarymanagement.controller.api;

import com.arobs.internship.librarymanagement.model.enums.RentRequestConfirmationResponse;
import com.arobs.internship.librarymanagement.service.RentRequestConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rentRequestConfirmation", produces = {MediaType.APPLICATION_JSON_VALUE})
public class RentRequestConfirmationController {

    @Autowired
    private RentRequestConfirmationService rentRequestConfirmationService;

    @RequestMapping(value = "/confirmation/{rentRequestConfirmationId}/{confirmation}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RentRequestConfirmationResponse> confirmation(@PathVariable("confirmation") RentRequestConfirmationResponse rentRequestConfirmation,
                                                                        @PathVariable("rentRequestConfirmationId") Integer rentRequestConfirmationId) {
        getRentRequestConfirmationService().mailConfirmationReponse(rentRequestConfirmation, rentRequestConfirmationId);

        return new ResponseEntity<>(rentRequestConfirmation, HttpStatus.OK);
    }

    public RentRequestConfirmationService getRentRequestConfirmationService() {
        return rentRequestConfirmationService;
    }
}
