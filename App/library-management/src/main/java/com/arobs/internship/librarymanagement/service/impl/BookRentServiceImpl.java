package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.BookRentRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.BookRentUpdateDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.model.BookRent;
import com.arobs.internship.librarymanagement.model.enums.BookRentStatus;
import com.arobs.internship.librarymanagement.repository.BookRentRepository;
import com.arobs.internship.librarymanagement.service.BookRentService;
import com.arobs.internship.librarymanagement.service.BookRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class BookRentServiceImpl implements BookRentService {

    @Autowired
    BookRentRepository bookRentRepository;

    @Autowired
    BookRequestService bookRequestService;

    @Override
    public BookRent save(BookRentRegistrationDTO bookRentRegistration) throws FoundException {

     return null;
    }

    @Override
    public BookRent retrieveById(int bookRentId) {
        return null;
    }

    @Override
    public boolean delete(int bookRentId) {
        return false;
    }

    @Override
    public Set<BookRent> retrieveAll() {
        return null;
    }

    @Override
    public Set<BookRent> retrieveByStatus(BookRentStatus bookRequestStatus) {
        return null;
    }

    @Override
    public BookRent update(BookRentUpdateDTO bookRentUpdateDTO, int bookRentId) throws FoundException {
        return null;
    }
}
