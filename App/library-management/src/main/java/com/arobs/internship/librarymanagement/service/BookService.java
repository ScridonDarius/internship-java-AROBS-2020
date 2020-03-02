package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.request.BookRegistrationDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.model.Book;

import java.util.Set;

public interface BookService {

    Book addBook(BookRegistrationDTO request) throws FoundException;

    Book retrieveBookByAuthorAndTitle(String author, String title);

    Book retrieveBookById(int id);

    Set<Book> getAll();

    boolean deleteBook(int id);
}
