package com.arobs.internship.librarymanagement.controller.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@ApiModel
public class TagResponseDTO {

    @ApiModelProperty
    private Long id;

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 50)
    private String tagName;

    public TagResponseDTO() {
    }

    public TagResponseDTO(Long id, @NotNull @Size(max = 50) String tagName) {
        this.id = id;
        this.tagName = tagName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        TagResponseDTO that = (TagResponseDTO) o;
        return id.equals(that.id) &&
                tagName.equals(that.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tagName);
    }
}
