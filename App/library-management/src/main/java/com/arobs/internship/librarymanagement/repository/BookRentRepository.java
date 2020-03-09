package com.arobs.internship.librarymanagement.repository;

import com.arobs.internship.librarymanagement.model.BookRent;

import java.util.List;

public interface BookRentRepository {

    BookRent create(BookRent bookRent);

    List<BookRent> findByStatus(String bookRentStatus);

    List<BookRent> findBookById(int id);

    void delete(BookRent book);

    List<BookRent> getAll();

    void updateBook(BookRent book);
}
