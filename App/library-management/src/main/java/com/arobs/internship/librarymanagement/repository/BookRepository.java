package com.arobs.internship.librarymanagement.repository;

import com.arobs.internship.librarymanagement.model.Book;

import java.sql.SQLException;

public interface BookRepository {

    Book save(Book book);

    Book findBook(String author, String title);
}
