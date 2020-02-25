package com.arobs.internship.librarymanagement.controller.api.response;

import com.arobs.internship.librarymanagement.model.enums.EmployeeRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel
public class EmployeeUpdateDTO {

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 20)
    private String firstName;

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 20)
    private String lastName;

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 50)
    private String email;

    public EmployeeUpdateDTO() {
    }

    public EmployeeUpdateDTO(@NotNull @Size(max = 20) String firstName, @NotNull @Size(max = 20) String lastName, @NotNull @Size(max = 50) String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
