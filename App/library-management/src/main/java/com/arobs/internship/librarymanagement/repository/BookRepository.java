package com.arobs.internship.librarymanagement.repository;

import com.arobs.internship.librarymanagement.model.Book;

import java.sql.SQLException;

public interface BookRepository {

    Book insertBook(Book book);
}
