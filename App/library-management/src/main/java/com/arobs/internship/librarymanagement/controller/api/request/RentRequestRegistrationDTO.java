package com.arobs.internship.librarymanagement.controller.api.request;

import com.arobs.internship.librarymanagement.model.enums.RentRequestStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@ApiModel
public class RentRequestRegistrationDTO {

    @ApiModelProperty(required = true)
    private LocalDateTime requestDate;

    @ApiModelProperty(required = true)
    @Enumerated
    private RentRequestStatus rentRequestStatus;

    @ApiModelProperty(required = true)
    @NotNull
    private int employeeId;

    @ApiModelProperty(required = true)
    @NotNull
    private int bookId;

    public RentRequestRegistrationDTO() {
    }

    public RentRequestRegistrationDTO(LocalDateTime requestDate, RentRequestStatus rentRequestStatus, @NotNull int employeeId, @NotNull int bookId) {
        this.requestDate = requestDate;
        this.rentRequestStatus = rentRequestStatus;
        this.employeeId = employeeId;
        this.bookId = bookId;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public RentRequestStatus getRentRequestStatus() {
        return rentRequestStatus;
    }

    public void setRentRequestStatus(RentRequestStatus rentRequestStatus) {
        this.rentRequestStatus = rentRequestStatus;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
