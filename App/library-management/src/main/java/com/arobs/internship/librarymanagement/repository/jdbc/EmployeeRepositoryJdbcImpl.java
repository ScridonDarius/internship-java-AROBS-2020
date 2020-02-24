package com.arobs.internship.librarymanagement.repository.jdbc;

import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.repository.jdbc.mapper.EmployeeMapper;
import com.arobs.internship.librarymanagement.repository.EmployeeRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class EmployeeRepositoryJdbcImpl implements EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepositoryJdbcImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int createEmployee(Employee employee) {
        return jdbcTemplate.update("INSERT INTO employee(user_name,first_name,last_name,email,password,Role,Status,create_date) VALUES(?,?,?,?,MD5(?),?,?,?)", employee.getUserName(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPassword(), employee.getEmployeeRole().toString(), employee.getEmployeeStatus().toString(), employee.getCreateDate());
    }

    @Override
    public Employee findEmployee(String userName) {
        return jdbcTemplate.queryForObject("SELECT * FROM employee WHERE user_name = ?", new Object[]{userName}, new EmployeeMapper());
    }

    @Override
    public boolean updateEmployee(String userName, Employee employee) {
        return false;
    }

    @Override
    public boolean deleteEmployee(String userName) {
        return jdbcTemplate.update("DELETE FROM tag WHERE user_name = ?", userName) > 0;
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT * FROM employee", new EmployeeMapper());
    }
}

