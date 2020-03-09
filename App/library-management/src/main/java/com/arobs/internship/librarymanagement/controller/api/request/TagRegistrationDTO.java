package com.arobs.internship.librarymanagement.controller.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel
public class TagRegistrationDTO {

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 50)
    private String tagName;

    public TagRegistrationDTO() {
    }

    public TagRegistrationDTO(@NotNull @Size(max = 50) String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
