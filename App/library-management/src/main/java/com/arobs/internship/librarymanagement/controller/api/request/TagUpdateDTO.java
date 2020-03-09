package com.arobs.internship.librarymanagement.controller.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel
@JsonIgnoreProperties(value = "id", allowGetters = true)
public class TagUpdateDTO {

    @ApiModelProperty
    private int id;

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 150)
    private String tagName;

    public TagUpdateDTO() {
    }

    public TagUpdateDTO(@NotNull @Size(max = 150) String tagName) {
        this.tagName = tagName;
    }

    public TagUpdateDTO(int id, @NotNull @Size(max = 150) String tagName) {
        this.id = id;
        this.tagName = tagName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
