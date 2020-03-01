package com.arobs.internship.librarymanagement.repository;

import com.arobs.internship.librarymanagement.model.Book;

import java.util.List;

public interface BookRepository {

    Book save(Book book);

    Book findBook(String author, String title);

    Book findBookById(int id);

    List<Book> getAll();
}
