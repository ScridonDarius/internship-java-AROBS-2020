package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.model.RentRequestConfirmation;

import java.util.List;

public interface RentRequestConfirmationService {

    RentRequestConfirmation save(RentRequestConfirmation bookRent);

    RentRequestConfirmation retrieveById(int rentConfirmationId);

    boolean delete(RentRequestConfirmation rentRequest);

    List<RentRequestConfirmation> findAll();

    void update(RentRequestConfirmation rentRequest);

    List<RentRequestConfirmation> orderByConfirmationDate();
}
