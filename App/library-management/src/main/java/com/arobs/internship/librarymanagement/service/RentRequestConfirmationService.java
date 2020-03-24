package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.model.RentRequestConfirmation;
import com.arobs.internship.librarymanagement.model.enums.RentRequestConfirmationResponse;
import com.arobs.internship.librarymanagement.model.enums.RentRequestConfirmationStatus;

import java.util.List;

public interface RentRequestConfirmationService {

    RentRequestConfirmation save(RentRequestConfirmation bookRent);

    RentRequestConfirmation retrieveById(int rentConfirmationId);

    boolean delete(RentRequestConfirmation rentRequest);

    List<RentRequestConfirmation> findAll();

    void update(RentRequestConfirmation rentRequest);

    RentRequestConfirmationResponse mailConfirmationReponse(RentRequestConfirmationResponse rentRequestConfirmationResponse, int rentRequestId);

    List<RentRequestConfirmation> orderByConfirmationDate();

    void updateStatus(RentRequestConfirmationStatus rentRequestConfirmationStatus, int rentConfirmationId);

}
