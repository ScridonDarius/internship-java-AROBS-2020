package com.arobs.internship.librarymanagement.controller.api.response;

import com.arobs.internship.librarymanagement.model.enums.BookRentStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@ApiModel
public class BookRentResponseDTO {

    @ApiModelProperty
    private int id;

    @ApiModelProperty(required = true)
    @NotNull
    private LocalDateTime rentalDate;

    @ApiModelProperty(required = true)
    private LocalDateTime returnDate;

    @ApiModelProperty(required = true)
    @NotNull
    @Enumerated
    private BookRentStatus bookRentStatus;


    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 50)
    private String note;

    @ApiModelProperty(required = true)
    @NotNull
    private BookDTO book;

    @ApiModelProperty(required = true)
    @NotNull
    private BookEmployeeDTO employee;

    @ApiModelProperty(required = true)
    @NotNull
    private BookCopyDTO copy;

    public BookRentResponseDTO() {
    }

    public BookRentResponseDTO(int id, @NotNull LocalDateTime rentalDate, LocalDateTime returnDate, @NotNull BookRentStatus bookRentStatus, @NotNull @Size(max = 50) String note, @NotNull BookDTO book, @NotNull BookEmployeeDTO employee, @NotNull BookCopyDTO copy) {
        this.id = id;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.bookRentStatus = bookRentStatus;
        this.note = note;
        this.book = book;
        this.employee = employee;
        this.copy = copy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDateTime rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public BookRentStatus getBookRentStatus() {
        return bookRentStatus;
    }

    public void setBookRentStatus(BookRentStatus bookRentStatus) {
        this.bookRentStatus = bookRentStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public BookEmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(BookEmployeeDTO employee) {
        this.employee = employee;
    }

    public BookCopyDTO getCopy() {
        return copy;
    }

    public void setCopy(BookCopyDTO copy) {
        this.copy = copy;
    }
}
