package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.request.BookRequestRegistrationDTO;
import com.arobs.internship.librarymanagement.model.BookRequest;

public interface BookRequestService {

    BookRequest save(BookRequestRegistrationDTO bookRequestRegistration);
}
