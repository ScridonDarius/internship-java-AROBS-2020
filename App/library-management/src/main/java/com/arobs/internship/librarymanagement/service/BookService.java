package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.request.BookRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.EmployeeRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeResponseDTO;

public interface BookService {

    BookResponseDTO insertBook(BookRegistrationDTO request);

    BookResponseDTO retrieveBookByAuthorAndTitle(String author, String title);
}
