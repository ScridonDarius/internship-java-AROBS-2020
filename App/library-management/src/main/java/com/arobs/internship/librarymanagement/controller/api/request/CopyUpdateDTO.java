package com.arobs.internship.librarymanagement.controller.api.request;

import com.arobs.internship.librarymanagement.model.enums.CopyCondition;
import com.arobs.internship.librarymanagement.model.enums.CopyStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel
public class CopyUpdateDTO {

    @ApiModelProperty(required = true)
    @NotNull
    @Enumerated
    private CopyCondition copyCondition;

    @ApiModelProperty(required = true)
    @NotNull
    @Enumerated
    private CopyStatus copyStatus;

    public CopyUpdateDTO() {
    }

    public CopyUpdateDTO(@NotNull CopyStatus copyStatus) {
        this.copyStatus = copyStatus;
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
}
