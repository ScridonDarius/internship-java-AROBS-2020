package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.model.RentRequestConfirmation;
import com.arobs.internship.librarymanagement.repository.RentRequestConfirmationRepository;
import com.arobs.internship.librarymanagement.service.RentRequestConfirmationService;
import com.arobs.internship.librarymanagement.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RentRequestConfirmationServiceImpl implements RentRequestConfirmationService {

    @Autowired
    private RentRequestConfirmationRepository rentRequestConfirmationRepository;

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
    public List<RentRequestConfirmation> findAll() {
        return getRentRequestConfirmationRepository().findAll();
    }

    @Transactional
    @Override
    public void update(RentRequestConfirmation rentRequest) {

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
