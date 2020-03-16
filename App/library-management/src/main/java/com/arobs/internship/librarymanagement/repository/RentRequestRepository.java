package com.arobs.internship.librarymanagement.repository;

import com.arobs.internship.librarymanagement.model.RentRequest;

import java.util.List;

public interface RentRequestRepository {

    RentRequest save(RentRequest bookRent);

    List<RentRequest> findByStatus(String rentRequestStatus);

    List<RentRequest> findById(int rentId);

    void delete(RentRequest rentRequest);

    List<RentRequest> findAll();

    void update(RentRequest rentRequest);

    List<RentRequest> orderByRentDate();
}
