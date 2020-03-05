package com.arobs.internship.librarymanagement.controller.api.request;

import com.arobs.internship.librarymanagement.controller.api.response.TagBookResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@ApiModel
public class BookRegistrationDTO {

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
    private Set<TagBookResponseDTO> tags;

    public BookRegistrationDTO() {
    }

    public BookRegistrationDTO(@NotNull @Size(max = 50) String title, @NotNull @Size(max = 50) String author, @NotNull @Size(max = 100) String description, @NotNull Set<TagBookResponseDTO> tags) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.tags = tags;
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

    public Set<TagBookResponseDTO> getTags() {
        return tags;
    }

    public void setTags(Set<TagBookResponseDTO> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookRegistrationDTO that = (BookRegistrationDTO) o;
        return title.equals(that.title) &&
                author.equals(that.author) &&
                description.equals(that.description) &&
                tags.equals(that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, description, tags);
    }
}
