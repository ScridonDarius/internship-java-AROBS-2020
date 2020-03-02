package com.arobs.internship.librarymanagement.repository;

import com.arobs.internship.librarymanagement.model.Book;

import java.util.List;

public interface BookRepository {

    Book save(Book book);

    List<Book> findBook(String author, String title);

    Book findBookById(int id);

    void delete(Book book);

    List<Book> getAll();
}
