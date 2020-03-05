package com.arobs.internship.librarymanagement.controller.api.response;

import com.arobs.internship.librarymanagement.model.enums.CopyCondition;
import com.arobs.internship.librarymanagement.model.enums.CopyStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel
public class CopyResponseDTO {

    @ApiModelProperty
    private int id;

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 20)
    private String isbn;

    @ApiModelProperty(required = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private CopyCondition copyCondition;


    @ApiModelProperty(required = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private CopyStatus copyStatus;

    @ApiModelProperty(required = true)
    private BookCopyDTO book;

    public CopyResponseDTO() {
    }

    public CopyResponseDTO(int id, @NotNull @Size(max = 20) String isbn, @NotNull CopyCondition copyCondition, @NotNull CopyStatus copyStatus, BookCopyDTO book) {
        this.id = id;
        this.isbn = isbn;
        this.copyCondition = copyCondition;
        this.copyStatus = copyStatus;
        this.book = book;
    }

    public CopyResponseDTO(int id, @NotNull @Size(max = 20) String isbn, @NotNull CopyCondition copyCondition, @NotNull CopyStatus copyStatus) {
        this.id = id;
        this.isbn = isbn;
        this.copyCondition = copyCondition;
        this.copyStatus = copyStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public CopyCondition getCopyCondition() {
        return copyCondition;
    }

    public void setCopyCondition(CopyCondition copyCondition) {
        this.copyCondition = copyCondition;
    }

    public CopyStatus getCopyStatus() {
        return copyStatus;
    }

    public void setCopyStatus(CopyStatus copyStatus) {
        this.copyStatus = copyStatus;
    }

    public BookCopyDTO getBook() {
        return book;
    }

    public void setBook(BookCopyDTO book) {
        this.book = book;
    }
}
