package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.request.BookRequestRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.BookRequestUpdateDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.model.BookRequest;
import com.arobs.internship.librarymanagement.model.enums.BookRequestStatus;

import javax.transaction.Transactional;
import java.util.Set;

public interface BookRequestService {

    BookRequest save(BookRequestRegistrationDTO bookRequestRegistration) throws FoundException;

    @Transactional
    BookRequest retrieveByAuthorAndTitle(String author, String title);

    @Transactional
    BookRequest retrieveById(int bookRequestId);

    @Transactional
    boolean delete(int bookRequestId);

    @Transactional
    Set<BookRequest> retrieveAll();

    @Transactional
    Set<BookRequest> retrieveByStatus(BookRequestStatus bookRequestStatus);

    @Transactional
    BookRequest update(BookRequestUpdateDTO bookRequestUpdateDTO, int bookRequestId)throws FoundException;
}
