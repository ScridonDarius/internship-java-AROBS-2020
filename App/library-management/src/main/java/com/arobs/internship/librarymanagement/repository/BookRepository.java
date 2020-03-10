package com.arobs.internship.librarymanagement.repository;

import com.arobs.internship.librarymanagement.model.Book;

import java.util.List;

public interface BookRepository {

    Book save(Book book);

    List<Book> findByAuthorAndTitle(String author, String title);

    List<Book> findById(int id);

    void delete(Book book);

    List<Book> getAll();

    void update(Book book);
}
