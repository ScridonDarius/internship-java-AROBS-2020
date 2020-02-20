package com.arobs.internship.librarymanagement.repository;

import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.model.Tag;

import java.sql.SQLException;

public interface EmployeeRepository {

    int createEmployee(Employee employee);
}
