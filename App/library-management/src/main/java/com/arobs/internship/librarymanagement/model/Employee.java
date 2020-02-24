package com.arobs.internship.librarymanagement.model;

import com.arobs.internship.librarymanagement.model.enums.EmployeeRole;
import com.arobs.internship.librarymanagement.model.enums.EmployeeStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue
    @Column(name ="employee_id")
    private int id;

    @Column(nullable = false, length = 50, unique = true, name = "user_name")
    private String userName;

    @Column(nullable = false, length = 20, name = "first_name")
    private String firstName;

    @Column(nullable = false, length = 20, name = "last_name")
    private String lastName;

    @Column(nullable = false, length = 50, name = "password")
    private String password;

    @Column(nullable = false, length = 50, name = "email")
    private String email;

    @Column(nullable = false, length = 50, name = "Role")
    @Enumerated(EnumType.STRING)
    private EmployeeRole employeeRole;

    @Column(nullable = false, length = 50, name = "Status")
    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;

    @Column(nullable = false, length = 50, name = "create_date")
    private LocalDateTime createDate;

    public Employee() {
    }

    public Employee(int id, String userName, String firstName, String lastName, String password, String email, EmployeeRole employeeRole, EmployeeStatus employeeStatus, LocalDateTime createDate) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.employeeRole = employeeRole;
        this.employeeStatus = employeeStatus;
        this.createDate = createDate;
    }

    public Employee(String userName, String firstName, String lastName, String password, String email, EmployeeRole employeeRole, EmployeeStatus employeeStatus, LocalDateTime createDate) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.employeeRole = employeeRole;
        this.employeeStatus = employeeStatus;
        this.createDate = createDate;
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

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
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
                createDate.equals(employee.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, firstName, lastName, password, email, employeeRole, employeeStatus, createDate);
    }
}
