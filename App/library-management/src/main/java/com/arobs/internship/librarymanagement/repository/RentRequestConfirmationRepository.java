package com.arobs.internship.librarymanagement.repository;

import com.arobs.internship.librarymanagement.model.RentRequestConfirmation;

import java.util.List;

public interface RentRequestConfirmationRepository {

    RentRequestConfirmation save(RentRequestConfirmation bookRent);

    List<RentRequestConfirmation> findById(int rentConfirmationId);

    void delete(RentRequestConfirmation rentRequest);

    List<RentRequestConfirmation> findAll();

    void update(RentRequestConfirmation rentRequest);

    List<RentRequestConfirmation> orderByConfirmationDate();
}
