package com.arobs.internship.librarymanagement.model;

import com.arobs.internship.librarymanagement.model.enums.BookRentStatus;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

//@Entity
//@Table(name = "book_rent")
public class BookRent {

    private Long id;
    private Employee employee;
    private Book book;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    private BookRentStatus bookRentStatus;
    private String note;


}
