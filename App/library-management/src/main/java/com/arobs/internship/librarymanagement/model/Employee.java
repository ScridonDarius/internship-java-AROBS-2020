package com.arobs.internship.librarymanagement.model;

import com.arobs.internship.librarymanagement.model.enums.EmployeeRole;
import com.arobs.internship.librarymanagement.model.enums.EmployeeStatus;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.Objects;

public class Employee {

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private Blob password;
    private String email;
    private EmployeeRole employeeRole;
    private EmployeeStatus employeeStatus;
    private LocalDateTime lastLoginDate;
    private Blob photo;

    public Employee() {
    }

    public Employee(Long id, String userName, String firstName, String lastName, Blob password, String email, EmployeeRole employeeRole, EmployeeStatus employeeStatus, LocalDateTime lastLoginDate, Blob photo) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.employeeRole = employeeRole;
        this.employeeStatus = employeeStatus;
        this.lastLoginDate = lastLoginDate;
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Blob getPassword() {
        return password;
    }

    public void setPassword(Blob password) {
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

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                userName.equals(employee.userName) &&
                firstName.equals(employee.firstName) &&
                lastName.equals(employee.lastName) &&
                password.equals(employee.password) &&
                email.equals(employee.email) &&
                employeeRole == employee.employeeRole &&
                employeeStatus == employee.employeeStatus &&
                Objects.equals(lastLoginDate, employee.lastLoginDate) &&
                Objects.equals(photo, employee.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, firstName, lastName, password, email, employeeRole, employeeStatus, lastLoginDate, photo);
    }
}
