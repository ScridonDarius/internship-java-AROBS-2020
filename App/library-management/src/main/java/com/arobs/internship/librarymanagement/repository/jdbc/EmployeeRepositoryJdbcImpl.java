package com.arobs.internship.librarymanagement.repository.jdbc;

import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository("repo")
public class EmployeeRepositoryJdbcImpl implements EmployeeRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepositoryJdbcImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int createEmployee(Employee employee) {

       return  jdbcTemplate.update("INSERT INTO employee(user_name,first_name,last_name,email,password,Role,Status,create_date) VALUES(?,?,?,?,MD5(?),?,?,?)", employee.getUserName(),employee.getFirstName(),employee.getLastName(),employee.getEmail(),employee.getPassword(),employee.getEmployeeRole().toString(),employee.getEmployeeStatus().toString(),employee.getCreateDate());

    }
}
