package com.arobs.internship.librarymanagement.controller.api.request;

import com.arobs.internship.librarymanagement.model.enums.BookRentStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@ApiModel
public class BookRentRegistrationDTO {

    @ApiModelProperty(required = true)
    @NotNull
    private LocalDateTime rentalDate;

    @Enumerated
    private BookRentStatus bookRentStatus;

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 50)
    private String note;

    @ApiModelProperty(required = true)
    @NotNull
    private int bookId;

    @ApiModelProperty(required = true)
    @NotNull
    private int employeeId;

    @ApiModelProperty(required = true)
    @NotNull
    private int copyId;

    public BookRentRegistrationDTO() {
    }

    public BookRentRegistrationDTO(@NotNull LocalDateTime rentalDate, BookRentStatus bookRentStatus, @NotNull @Size(max = 50) String note, @NotNull int bookId, @NotNull int employeeId, @NotNull int copyId) {
        this.rentalDate = rentalDate;
        this.bookRentStatus = bookRentStatus;
        this.note = note;
        this.bookId = bookId;
        this.employeeId = employeeId;
        this.copyId = copyId;
    }

    public LocalDateTime getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDateTime rentalDate) {
        this.rentalDate = rentalDate;
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

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getCopyId() {
        return copyId;
    }

    public void setCopyId(int copyId) {
        this.copyId = copyId;
    }
}
