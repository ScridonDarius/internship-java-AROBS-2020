package com.arobs.internship.librarymanagement.controller.api.response;

import com.arobs.internship.librarymanagement.model.enums.BookRequestStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@ApiModel
public class BookRequestResponseDTO {

    @ApiModelProperty
    private int id;

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 50)
    private String title;

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 50)
    private String author;

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 50)
    private String publishingCompany;

    @ApiModelProperty(required = true)
    @NotNull
    private int copyNumber;

    @ApiModelProperty(required = true)
    @NotNull
    private BigDecimal totalCost;

    @NotNull
    @Enumerated
    private BookRequestStatus bookRequestStatus;

    @ApiModelProperty(required = true)
    @NotNull
    private BookEmployeeDTO employee;

    public BookRequestResponseDTO() {
    }

    public BookRequestResponseDTO(int id, @NotNull @Size(max = 50) String title, @NotNull @Size(max = 50) String author, @NotNull @Size(max = 50) String publishingCompany, @NotNull int copyNumber, @NotNull BigDecimal totalCost, @NotNull BookRequestStatus bookRequestStatus, @NotNull BookEmployeeDTO employee) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishingCompany = publishingCompany;
        this.copyNumber = copyNumber;
        this.totalCost = totalCost;
        this.bookRequestStatus = bookRequestStatus;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishingCompany() {
        return publishingCompany;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
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

    public BookEmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(BookEmployeeDTO employee) {
        this.employee = employee;
    }
}
