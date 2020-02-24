package com.arobs.internship.librarymanagement.model;

import com.arobs.internship.librarymanagement.model.enums.RentRequestStatus;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

//@Entity
//@Table(name = "rent_request")
public class RentRequest {

    private Long id;
    private LocalDateTime requestDate;
    private Employee employee;
    private Book book;
    private RentRequestStatus rentRequestStatus;
}
