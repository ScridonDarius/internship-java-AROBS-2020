package com.arobs.internship.librarymanagement.controller.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel
public class CopyRegistrationDTO {

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 20)
    private String isbn;

    public CopyRegistrationDTO() {
    }

    public CopyRegistrationDTO(@NotNull @Size(max = 20) String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}
