package com.arobs.internship.librarymanagement.repository;

import com.arobs.internship.librarymanagement.model.Employee;

import java.util.List;

public interface EmployeeRepository {

    int createEmployee(Employee employee);

    Employee findEmployee(String userName);

    void updateEmployee(String userName, Employee employee);

    void deleteEmployee(String userName);

    List<Employee> findAll();

    Employee findEmployeeByEmail(String email);

    Employee updatePassword(String userName, String password);
}
