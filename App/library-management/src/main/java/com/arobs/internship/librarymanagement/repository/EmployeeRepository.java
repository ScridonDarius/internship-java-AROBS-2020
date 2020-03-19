package com.arobs.internship.librarymanagement.repository;

import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.model.enums.EmployeeStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeRepository {

    int save(Employee employee);

    List<Employee> findByUserName(String userName);

    void update(String userName, Employee employee);

    void delete(int id);

    List<Employee> getAll();

    List<Employee> findByEmail(String email);

    List<Employee> findById(int id);

    Employee updatePassword(String userName, String password);

    void updateRemovalSuspended(LocalDateTime removalSuspended, int employeeId);

    void updateStatus(String employeeStatus, int employeeId);
}
