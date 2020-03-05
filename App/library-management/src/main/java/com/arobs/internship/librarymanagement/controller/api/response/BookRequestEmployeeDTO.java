package com.arobs.internship.librarymanagement.controller.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BookRequestEmployeeDTO {

    @ApiModelProperty
    private int id;

    public BookRequestEmployeeDTO() {
    }

    public BookRequestEmployeeDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
