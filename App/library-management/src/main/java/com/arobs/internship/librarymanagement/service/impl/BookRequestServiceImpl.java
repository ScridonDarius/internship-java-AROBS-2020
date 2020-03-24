package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.BookRequestRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.BookRequestUpdateDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.model.BookRequest;
import com.arobs.internship.librarymanagement.model.enums.BookRequestStatus;
import com.arobs.internship.librarymanagement.repository.BookRequestRepository;
import com.arobs.internship.librarymanagement.service.BookRequestService;
import com.arobs.internship.librarymanagement.service.BookService;
import com.arobs.internship.librarymanagement.service.converter.ListToSetConverter;
import com.arobs.internship.librarymanagement.mapperConverter.BookRequestMapperConverter;
import com.arobs.internship.librarymanagement.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Set;

@Service
public class BookRequestServiceImpl implements BookRequestService {

    @Autowired
    BookService bookService;

    final BookRequestRepository bookRequestRepository;

    public BookRequestServiceImpl(BookRequestRepository bookRequestRepository) {
        this.bookRequestRepository = bookRequestRepository;
    }

    @Transactional
    @Override
    public BookRequest save(BookRequestRegistrationDTO bookRequestRegistration) throws FoundException {
        if (Objects.isNull(bookService.retrieveByAuthorAndTitle(bookRequestRegistration.getAuthor(), bookRequestRegistration.getTitle())) && !Objects.nonNull(retrieveByAuthorAndTitle(bookRequestRegistration.getAuthor(), bookRequestRegistration.getTitle()))) {
            return getBookRequestRepository().save(BookRequestMapperConverter.generateEntityFromDTORegistration(bookRequestRegistration));
        } else {
            throw new FoundException();
        }
    }

    @Transactional
    @Override
    public BookRequest retrieveByAuthorAndTitle(String author, String title) {
        return ValidationService.safeGetUniqueResult(getBookRequestRepository().findByAuthorAndTitle(author, title));
    }

    @Transactional
    @Override
    public BookRequest retrieveByAuthorTitleAndEmployeeId(String author, String title, int employeeId) {
        return ValidationService.safeGetUniqueResult(getBookRequestRepository().findByAuthorTitleAndEmployeeId(author, title, employeeId));
    }

    @Transactional
    @Override
    public BookRequest retrieveById(int bookRequestId) {
        return ValidationService.safeGetUniqueResult(getBookRequestRepository().findById(bookRequestId));
    }

    @Transactional
    @Override
    public boolean delete(int bookRequestId) {
        final BookRequest bookRequest = retrieveById(bookRequestId);
        if (!Objects.isNull(bookRequest)) {
            getBookRequestRepository().delete(bookRequest);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public Set<BookRequest> retrieveAll() {
        return ListToSetConverter.convertListToSet(getBookRequestRepository().getAll());
    }

    @Transactional
    @Override
    public Set<BookRequest> retrieveByStatus(BookRequestStatus bookRequestStatus) {
        return Set.copyOf(getBookRequestRepository().findByStatus(bookRequestStatus.toString()));
    }

    @Transactional
    @Override
    public BookRequest update(BookRequestUpdateDTO bookRequestUpdateDTO, int bookRequestId) throws FoundException {
        BookRequest bookRequest = retrieveById(bookRequestId);

        if (!Objects.isNull(bookRequest)) {
            bookRequest.setBookRequestStatus(bookRequestUpdateDTO.getBookRequestStatus());
            bookRequest.setCopyNumber(bookRequestUpdateDTO.getCopyNumber());
            bookRequest.setTotalCost(bookRequestUpdateDTO.getTotalCost());
        } else throw new FoundException();

        return bookRequest;
    }

    protected BookRequestRepository getBookRequestRepository() {
        return bookRequestRepository;
    }
}
