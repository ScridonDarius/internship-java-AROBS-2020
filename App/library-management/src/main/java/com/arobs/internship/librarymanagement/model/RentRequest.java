package com.arobs.internship.librarymanagement.model;

import com.arobs.internship.librarymanagement.model.enums.RentRequestStatus;

import java.time.LocalDateTime;

public class RentRequest {

    private Long id;
    private LocalDateTime requestDate;
    private Employee employee;
    private Book book;
    private RentRequestStatus rentRequestStatus;
}
