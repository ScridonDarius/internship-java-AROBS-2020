package com.arobs.internship.librarymanagement.controller.api.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@ApiModel
@JsonIgnoreProperties(value = "id", allowGetters = true)
public class TagResponseDTO {

    @ApiModelProperty
    private int id;

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 50)
    private String tagName;

    public TagResponseDTO() {
    }

    public TagResponseDTO(@NotNull @Size(max = 50) String tagName) {
        this.tagName = tagName;
    }

    public TagResponseDTO(int id, @NotNull @Size(max = 50) String tagName) {
        this.id = id;
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagResponseDTO that = (TagResponseDTO) o;
        return Objects.equals(id, that.id) &&
                tagName.equals(that.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tagName);
    }
}
