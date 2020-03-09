package com.arobs.internship.librarymanagement.controller.api.request;

import com.arobs.internship.librarymanagement.model.enums.EmployeeRole;
import com.arobs.internship.librarymanagement.model.enums.EmployeeStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@ApiModel
public class EmployeeRegistrationDTO {

    @ApiModelProperty
    private int id;

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 20)
    private String userName;

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
    @Size(max = 20)
    private String password;

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 50)
    private String email;

    @ApiModelProperty(required = true)
    @NotNull
    @Size(max = 10)
    @Enumerated
    private EmployeeRole employeeRole;

    @ApiModelProperty(required = true)
    @NotNull
    @Enumerated
    private EmployeeStatus employeeStatus;

    @ApiModelProperty(required = true)
    @NotNull
    private LocalDateTime createDate;

    public EmployeeRegistrationDTO() {
    }

    public EmployeeRegistrationDTO(@NotNull @Size(max = 20) String userName, @NotNull @Size(max = 20) String firstName, @NotNull @Size(max = 20) String lastName, @NotNull @Size(max = 20) String password, @NotNull @Size(max = 50) String email, @NotNull @Size(max = 10) EmployeeRole employeeRole, @NotNull EmployeeStatus employeeStatus, @NotNull LocalDateTime createDate) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.employeeRole = employeeRole;
        this.employeeStatus = employeeStatus;
        this.createDate = createDate;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmployeeRole getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(EmployeeRole employeeRole) {
        this.employeeRole = employeeRole;
    }
}
