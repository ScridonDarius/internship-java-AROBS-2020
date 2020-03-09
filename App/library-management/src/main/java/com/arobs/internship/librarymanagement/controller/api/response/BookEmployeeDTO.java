package com.arobs.internship.librarymanagement.controller.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BookEmployeeDTO {

    @ApiModelProperty
    private int id;

    public BookEmployeeDTO() {
    }

    public BookEmployeeDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
