package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.model.RentRequest;
import com.arobs.internship.librarymanagement.model.RentRequestConfirmation;
import com.arobs.internship.librarymanagement.model.enums.RentRequestConfirmationResponse;
import com.arobs.internship.librarymanagement.model.enums.RentRequestConfirmationStatus;
import com.arobs.internship.librarymanagement.model.enums.RentRequestStatus;
import com.arobs.internship.librarymanagement.repository.RentRequestConfirmationRepository;
import com.arobs.internship.librarymanagement.service.RentRequestConfirmationService;
import com.arobs.internship.librarymanagement.service.RentRequestService;
import com.arobs.internship.librarymanagement.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.ValidationEventLocator;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentRequestConfirmationServiceImpl implements RentRequestConfirmationService {

    @Autowired
    private RentRequestConfirmationRepository rentRequestConfirmationRepository;

    @Autowired
    private RentRequestService rentRequestService;

    @Transactional
    @Override
    public RentRequestConfirmation save(RentRequestConfirmation bookRentConfirmation) {
        return getRentRequestConfirmationRepository().save(bookRentConfirmation);
    }

    @Transactional
    @Override
    public RentRequestConfirmation retrieveById(int rentConfirmationId) {
        return ValidationService.safeGetUniqueResult(getRentRequestConfirmationRepository().findById(rentConfirmationId));
    }

    @Transactional
    @Override
    public boolean delete(RentRequestConfirmation rentRequestConfirmation) {
        final RentRequestConfirmation rentRequest = retrieveById(rentRequestConfirmation.getId());
        if (rentRequest == null) {
            return false;
        }
        getRentRequestConfirmationRepository().delete(rentRequest);

        return true;
    }

    @Transactional
    @Override
    public void updateStatus(RentRequestConfirmationStatus rentRequestConfirmationStatus, int rentConfirmationId) {
        getRentRequestConfirmationRepository().updateStatus(rentRequestConfirmationStatus.toString(), rentConfirmationId);
    }

    @Transactional
    @Override
    public List<RentRequestConfirmation> findAll() {
        return getRentRequestConfirmationRepository().findAll();
    }

    @Transactional
    @Override
    public void update(RentRequestConfirmation rentRequest) {
        getRentRequestConfirmationRepository().update(rentRequest);

    }

    @Transactional
    @Override
    public RentRequestConfirmationResponse mailConfirmationReponse(RentRequestConfirmationResponse rentRequestConfirmationResponse, int rentRequetConfirmationId) {
        RentRequestConfirmation rentRequestConfirmation = ValidationService.safeGetUniqueResult(getRentRequestConfirmationRepository().findById(rentRequetConfirmationId));
        RentRequest rentRequest = rentRequestService.retrieveById(rentRequestConfirmation.getRentRequestId().getId());
        if (rentRequestConfirmationResponse.equals(RentRequestConfirmationResponse.ACCEPT)) {

            rentRequest.setRentRequestStatus(RentRequestStatus.GRANTED);
            rentRequestService.update(rentRequest);

            return rentRequestConfirmationResponse;
        } else {

            rentRequest.setRentRequestStatus((RentRequestStatus.DECLINE));
            return rentRequestConfirmationResponse;
        }
    }

    @Transactional
    @Override
    public List<RentRequestConfirmation> orderByConfirmationDate() {
        return getRentRequestConfirmationRepository().orderByConfirmationDate();
    }

    protected RentRequestConfirmationRepository getRentRequestConfirmationRepository() {
        return rentRequestConfirmationRepository;
    }
}
