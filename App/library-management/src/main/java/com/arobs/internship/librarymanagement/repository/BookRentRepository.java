package com.arobs.internship.librarymanagement.repository;

import com.arobs.internship.librarymanagement.model.BookRent;

import java.util.List;

public interface BookRentRepository {

    BookRent save(BookRent bookRent);

    List<BookRent> findByStatus(String bookRentStatus);

    List<BookRent> findById(int rentId);

    void delete(BookRent book);

    List<BookRent> findAll();

    void update(BookRent book);

    List<BookRent> orderByRentDate();

    boolean findByBookIdAndEmployeeId(int bookId, int employeeId);


}
