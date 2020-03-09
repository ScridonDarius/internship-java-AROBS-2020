package com.arobs.internship.librarymanagement.repository;

import com.arobs.internship.librarymanagement.model.BookRequest;

import java.util.List;

public interface BookRequestRepository {

    BookRequest save(BookRequest bookRequest);

    List<BookRequest> findByAuthorAndTitle(String author, String title);

    List<BookRequest> findByAuthorTitleAndEmployeeId(String author,String title,int employeeId);

    void delete(BookRequest bookRequest);

    List<BookRequest> findById(int bookRequestId);

    List<BookRequest> getAll();

    void update(BookRequest bookRequest);

    List<BookRequest> findByStatus(String bookRequestStatus);
}
