package com.arobs.internship.librarymanagement.repository.jdbc;

import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.repository.EmployeeRepository;
import com.arobs.internship.librarymanagement.repository.jdbc.mapper.EmployeeMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EmployeeRepositoryJdbcImpl implements EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepositoryJdbcImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int save(Employee employee) {
        return jdbcTemplate.update("INSERT INTO employee(user_name,first_name,last_name,email,password,Role,Status,create_date) VALUES(?,?,?,?,MD5(?),?,?,?)", employee.getUserName(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPassword(), employee.getEmployeeRole().toString(), employee.getEmployeeStatus().toString(), employee.getCreateDate());
    }

    @Override
    public List<Employee> findByUserName(String userName) {
        jdbcTemplate.queryForObject("SELECT * FROM employee WHERE user_name = ?", new Object[]{userName}, new EmployeeMapper());
        return null;
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM tag WHERE employee_id = ?", id);
    }

    @Override
    public List<Employee> getAll() {
        return jdbcTemplate.query("SELECT * FROM employee", new EmployeeMapper());
    }

    @Override
    public List<Employee> findByEmail(String email) {
        jdbcTemplate.queryForObject("SELECT * FROM employee WHERE email = ?", new Object[]{email}, new EmployeeMapper());
        return null;
    }

    @Override
    public List<Employee> findById(int id) {
        return null;
    }

    @Override
    public Employee updatePassword(String userName, String password) {
        return null;
    }

    @Override
    public void updateStatus(String employeeStatus, int employeeId) {

    }

    @Override
    public void update(String userName, Employee employee) {
    }

    @Override
    public void updateRemovalSuspended(LocalDateTime removalSuspended, int employeeId) {

    }
}

