package com.arobs.internship.librarymanagement.controller.api.response;

import com.arobs.internship.librarymanagement.model.Tag;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@ApiModel
@JsonIgnoreProperties(value = "id", allowGetters = true)
public class BookResponseDTO {

    @ApiModelProperty
    private int id;

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 50)
    private String title;

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 50)
    private String author;

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 100)
    private String description;

    @ApiModelProperty(required = true)
    @NotNull
    private Set<TagResponseDTO> tags;

    public BookResponseDTO() {
    }

    public BookResponseDTO(int id, @NotNull @Size(max = 50) String title, @NotNull @Size(max = 50) String author, @NotNull @Size(max = 100) String description, @NotNull Set<TagResponseDTO> tags) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<TagResponseDTO> getTags() {
        return tags;
    }

    public void setTags(Set<TagResponseDTO> tags) {
        this.tags = tags;
    }
}