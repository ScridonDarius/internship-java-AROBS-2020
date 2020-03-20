package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.request.BookRentRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.BookRentUpdateDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.model.BookRent;
import com.arobs.internship.librarymanagement.model.enums.BookRentStatus;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface BookRentService {

    BookRent save(BookRentRegistrationDTO bookRentRegistration) throws FoundException;

    BookRent retrieveById(int rentId);

    @Transactional
    BookRent saveBookRent(BookRentRegistrationDTO bookRentRegistration);

    boolean delete(int bookRentId);

    Set<BookRent> retrieveAll();

    Set<BookRent> retrieveByStatus(BookRentStatus bookRequestStatus);

    BookRent update(BookRentUpdateDTO bookRentUpdateDTO, int bookRentId)throws FoundException;

    Set<BookRent> getBookRentsOrderedByDate();

    BookRent termExtension(int bookRentId);

    List<BookRent> retrieveByBookIdAndEmployeeId(int bookId, int employeeId);

}
