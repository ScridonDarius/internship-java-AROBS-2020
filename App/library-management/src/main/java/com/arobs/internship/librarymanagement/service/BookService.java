package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.request.BookRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.BookUpdateDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.model.Book;

import java.util.Set;

public interface BookService {

    Book save(BookRegistrationDTO request) throws FoundException;

    Book retrieveByAuthorAndTitle(String author, String title);

    Book retrieveById(int id);

    Set<Book> getAll();

    boolean delete(int id);

    Book update(BookUpdateDTO bookUpdateDTO, int bookId);
}
