package com.arobs.internship.librarymanagement.controller.api.request;

import com.arobs.internship.librarymanagement.model.enums.BookRequestStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel
public class BookRequestUpdateDTO {

    @ApiModelProperty(required = true)
    @NotNull
    private int copyNumber;

    @ApiModelProperty(required = true)
    @NotNull
    private BigDecimal totalCost;

    @Enumerated
    @NotNull
    private BookRequestStatus bookRequestStatus;

    public BookRequestUpdateDTO() {
    }

    public BookRequestUpdateDTO(@NotNull int copyNumber, @NotNull BigDecimal totalCost, @NotNull BookRequestStatus bookRequestStatus) {
        this.copyNumber = copyNumber;
        this.totalCost = totalCost;
        this.bookRequestStatus = bookRequestStatus;
    }

    public int getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(int copyNumber) {
        this.copyNumber = copyNumber;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BookRequestStatus getBookRequestStatus() {
        return bookRequestStatus;
    }

    public void setBookRequestStatus(BookRequestStatus bookRequestStatus) {
        this.bookRequestStatus = bookRequestStatus;
    }
}
