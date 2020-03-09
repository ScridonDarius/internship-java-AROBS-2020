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

    BookRequest retrieveByAuthorAndTitle(String author, String title);

    BookRequest retrieveByAuthorTitleAndEmployeeId(String author, String title, int employeeId);

    BookRequest retrieveById(int bookRequestId);

    boolean delete(int bookRequestId);

    Set<BookRequest> retrieveAll();

    Set<BookRequest> retrieveByStatus(BookRequestStatus bookRequestStatus);

    BookRequest update(BookRequestUpdateDTO bookRequestUpdateDTO, int bookRequestId)throws FoundException;
}
