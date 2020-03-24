package com.arobs.internship.librarymanagement.controller.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;

@ApiModel
public class StatisticsEmployeeDTO {

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 20)
    private String userName;

    @ApiModelProperty(required = true)
    private BigInteger numberOfBooks;

    public StatisticsEmployeeDTO() {
    }

    public StatisticsEmployeeDTO(@NotNull @Size(max = 20) String userName, BigInteger numberOfBooks) {
        this.userName = userName;
        this.numberOfBooks = numberOfBooks;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigInteger getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(BigInteger numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }
}

