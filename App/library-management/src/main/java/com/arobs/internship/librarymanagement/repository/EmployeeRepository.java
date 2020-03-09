package com.arobs.internship.librarymanagement.repository;

import com.arobs.internship.librarymanagement.model.Employee;

import java.util.List;

public interface EmployeeRepository {

    int save(Employee employee);

    List<Employee> findByUserName(String userName);

    void update(String userName, Employee employee);

    void delete(String userName);

    List<Employee> getAll();

    List<Employee> findByEmail(String email);

    Employee updatePassword(String userName, String password);
}
