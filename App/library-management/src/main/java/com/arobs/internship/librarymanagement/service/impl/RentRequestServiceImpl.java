package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.RentRequestRegistrationDTO;
import com.arobs.internship.librarymanagement.model.Copy;
import com.arobs.internship.librarymanagement.model.RentRequest;
import com.arobs.internship.librarymanagement.model.enums.CopyStatus;
import com.arobs.internship.librarymanagement.model.enums.RentRequestStatus;
import com.arobs.internship.librarymanagement.repository.RentRequestRepository;
import com.arobs.internship.librarymanagement.repository.hibernate.RentRequestRepositoryHibernateImpl;
import com.arobs.internship.librarymanagement.service.CopyService;
import com.arobs.internship.librarymanagement.service.RentRequestService;
import com.arobs.internship.librarymanagement.service.converter.ListToSetConverter;
import com.arobs.internship.librarymanagement.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.Set;

@Service
public class RentRequestServiceImpl implements RentRequestService {


    private final RentRequestRepositoryHibernateImpl rentRequestRepository;

    @Autowired
    CopyServiceImpl copyService;

    @Autowired
    BookServiceImpl bookService;

    @Autowired
    EmployeeServiceImpl employeeService;

    public RentRequestServiceImpl(RentRequestRepositoryHibernateImpl rentRequestRepository) {
        this.rentRequestRepository = rentRequestRepository;
    }

    @Transactional
    @Override
    public RentRequest save(RentRequestRegistrationDTO rentRequestRegistration) {

        // TODO : if employee and book is registrated for request throw a message(Allready exist in dataBase)

        Set<Copy> copies = getCopyService().retrieveByStatusAndBookId(rentRequestRegistration.getBookId(), CopyStatus.AVAILABLE);
        if (CollectionUtils.isEmpty(copies)) {
            RentRequest rentRequest = new RentRequest();

            rentRequest.setBook(bookService.retrieveById(rentRequestRegistration.getBookId()));
            rentRequest.setEmployee(employeeService.retrieveById(rentRequestRegistration.getEmployeeId()));
            rentRequest.setRentRequestStatus(RentRequestStatus.WAITING_AVAILABLE);
            rentRequest.setRequestDate(LocalDateTime.now());

            return getRentRequestRepository().save(rentRequest);

        } else
            throw new ValidationException("This book is available, please rent, dont make request for rent if is disponible");

    }

    @Transactional
    @Override
    public RentRequest retrieveById(int rentRequestId) {
        return ValidationService.safeGetUniqueResult(getRentRequestRepository().findById(rentRequestId));
    }

    @Transactional
    @Override
    public boolean delete(int rentRequestId) {
        final RentRequest rentRequest = retrieveById(rentRequestId);
        if (rentRequest == null) {
            return false;
        }
        getRentRequestRepository().delete(rentRequest);

        return true;
    }

    @Transactional
    @Override
    public Set<RentRequest> retrieveAll() {
        return ListToSetConverter.convertListToSet(getRentRequestRepository().findAll());
    }

    @Transactional
    @Override
    public Set<RentRequest> retrieveByStatus(RentRequestStatus bookRequestStatus) {
        return ListToSetConverter.convertListToSet(getRentRequestRepository().findByStatus(bookRequestStatus.toString()));
    }

    @Transactional
    @Override
    public void update(RentRequest rentRequest) {
        getRentRequestRepository().update(rentRequest.getRentRequestStatus().toString(), rentRequest.getId());
    }

    @Transactional
    @Override
    public Set<RentRequest> getBookRentsOrderedByDate() {
        return ListToSetConverter.convertListToSet(getRentRequestRepository().orderByRentDate());
    }

    public CopyService getCopyService() {
        return copyService;
    }

    public RentRequestRepository getRentRequestRepository() {
        return rentRequestRepository;
    }
}
