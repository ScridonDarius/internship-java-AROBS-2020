package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.request.RentRequestRegistrationDTO;
import com.arobs.internship.librarymanagement.model.RentRequest;
import com.arobs.internship.librarymanagement.model.enums.RentRequestStatus;

import java.util.Set;

public interface RentRequestService {

    RentRequest save(RentRequestRegistrationDTO rentRequestRegistration);

    RentRequest retrieveById(int rentRequestId);

    boolean delete(int rentRequestId);

    Set<RentRequest> retrieveAll();

    Set<RentRequest> retrieveByStatus(RentRequestStatus bookRequestStatus);

    Set<RentRequest> getRentRequestsOrderByDate();

    void update(RentRequest rentRequest);
}
