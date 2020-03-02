package com.arobs.internship.librarymanagement.controller.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@ApiModel
public class TagBookResponseDTO {

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 50)
    private String tagName;

    public TagBookResponseDTO() {
    }

    public TagBookResponseDTO(@NotNull @Size(max = 50) String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagBookResponseDTO that = (TagBookResponseDTO) o;
        return tagName.equals(that.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagName);
    }
}

