package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.request.BookRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookResponseDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;

import javax.transaction.Transactional;
import java.util.Set;

public interface BookService {

    BookResponseDTO addBook(BookRegistrationDTO request) throws FoundException;

    BookResponseDTO retrieveBookByAuthorAndTitle(String author, String title);

    BookResponseDTO retrieveBookById(int id);

    Set<BookResponseDTO> getAll();

    boolean deleteBook(int id);
}
