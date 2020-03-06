package com.arobs.internship.librarymanagement.controller.api.request;

import com.arobs.internship.librarymanagement.controller.api.response.TagBookResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@ApiModel
public class BookUpdateDTO {

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 100)
    private String description;

    @ApiModelProperty(required = true)
    @NotNull
    private Set<TagBookResponseDTO> tags;

    public BookUpdateDTO() {
    }

    public BookUpdateDTO(@NotNull @Size(max = 100) String description, @NotNull Set<TagBookResponseDTO> tags) {
        this.description = description;
        this.tags = tags;
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
}
