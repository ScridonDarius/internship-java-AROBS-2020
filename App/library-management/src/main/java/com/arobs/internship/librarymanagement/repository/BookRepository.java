package com.arobs.internship.librarymanagement.repository;

import com.arobs.internship.librarymanagement.model.Book;

import java.sql.SQLException;

public interface BookRepository {

    void insertBook(Book book) throws SQLException;
}
