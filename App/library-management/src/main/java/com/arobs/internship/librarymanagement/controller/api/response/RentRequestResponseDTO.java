package com.arobs.internship.librarymanagement.controller.api.response;

import com.arobs.internship.librarymanagement.model.enums.RentRequestStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@ApiModel
public class RentRequestResponseDTO {

    @ApiModelProperty
    private int id;

    @ApiModelProperty(required = true)
    @NotNull
    private LocalDateTime requestDate;

    @ApiModelProperty(required = true)
    @NotNull
    @Enumerated
    private RentRequestStatus rentRequestStatus;

    @ApiModelProperty(required = true)
    @NotNull
    private EmployeeDTO employee;

    @ApiModelProperty(required = true)
    @NotNull
    private BookDTO book;

    public RentRequestResponseDTO() {
    }

    public RentRequestResponseDTO(int id, @NotNull LocalDateTime requestDate, @NotNull RentRequestStatus rentRequestStatus, @NotNull EmployeeDTO employee, @NotNull BookDTO book) {
        this.id = id;
        this.requestDate = requestDate;
        this.rentRequestStatus = rentRequestStatus;
        this.employee = employee;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }
}
