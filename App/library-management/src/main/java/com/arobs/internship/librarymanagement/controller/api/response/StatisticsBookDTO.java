package com.arobs.internship.librarymanagement.controller.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;

@ApiModel
public class StatisticsBookDTO {

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 50)
    private String title;

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 50)
    private String author;

    @ApiModelProperty(required = true)
    private BigInteger readingsNumber;

    public StatisticsBookDTO() {
    }

    public StatisticsBookDTO(@NotNull @Size(max = 50) String title, @NotNull @Size(max = 50) String author, BigInteger readingsNumber) {
        this.title = title;
        this.author = author;
        this.readingsNumber = readingsNumber;
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

    public BigInteger getReadingsNumber() {
        return readingsNumber;
    }

    public void setReadingsNumber(BigInteger readingsNumber) {
        this.readingsNumber = readingsNumber;
    }
}
