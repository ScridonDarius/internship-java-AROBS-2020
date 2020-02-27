package com.arobs.internship.librarymanagement.repository;

import com.arobs.internship.librarymanagement.model.Employee;

import java.util.List;

public interface EmployeeRepository {

    int createEmployee(Employee employee);

    Employee findEmployee(String userName);

    boolean updateEmployee(String userName, Employee employee);

    boolean deleteEmployee(String userName);

    List<Employee> findAll();

    Employee findEmployeeByEmail(String email);

    Employee updatePassword(String userName, String password);
}
