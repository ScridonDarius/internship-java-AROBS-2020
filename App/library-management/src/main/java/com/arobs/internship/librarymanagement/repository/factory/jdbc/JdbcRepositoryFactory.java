package com.arobs.internship.librarymanagement.repository.factory.jdbc;

import com.arobs.internship.librarymanagement.repository.EmployeeRepository;
import com.arobs.internship.librarymanagement.repository.factory.RepositoryFactory;
import com.arobs.internship.librarymanagement.repository.jdbc.EmployeeRepositoryJdbcImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JdbcRepositoryFactory extends RepositoryFactory {

    @Autowired
    private EmployeeRepositoryJdbcImpl employeeRepositoryJdbc;

    @Override
    public EmployeeRepository getEmployeeRepository() {
        return employeeRepositoryJdbc;
    }
}