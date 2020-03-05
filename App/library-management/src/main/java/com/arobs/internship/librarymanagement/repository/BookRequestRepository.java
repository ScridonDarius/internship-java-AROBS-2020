package com.arobs.internship.librarymanagement.repository;

import com.arobs.internship.librarymanagement.model.BookRequest;

public interface BookRequestRepository {

    BookRequest save(BookRequest bookRequest);
}
