package com.arobs.internship.librarymanagement.controller.api.request;

import com.arobs.internship.librarymanagement.model.enums.BookRentStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@ApiModel
public class BookRentUpdateDTO {

    @ApiModelProperty(required = true)
    @NotNull
    @Enumerated
    private BookRentStatus bookRentStatus;

    public BookRentUpdateDTO() {
    }

    public BookRentUpdateDTO(@NotNull BookRentStatus bookRentStatus) {
        this.bookRentStatus = bookRentStatus;
    }

    public BookRentStatus getBookRentStatus() {
        return bookRentStatus;
    }

    public void setBookRentStatus(BookRentStatus bookRentStatus) {
        this.bookRentStatus = bookRentStatus;
    }
}
