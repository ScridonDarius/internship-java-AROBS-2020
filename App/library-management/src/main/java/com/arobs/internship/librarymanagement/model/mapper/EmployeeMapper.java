package com.arobs.internship.librarymanagement.model.mapper;

import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.model.enums.EmployeeRole;
import com.arobs.internship.librarymanagement.model.enums.EmployeeStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getLong("employee_id"));
        employee.setUserName(resultSet.getString("user_name"));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setEmail(resultSet.getString("email"));
        employee.setPassword(resultSet.getString("password"));
        employee.setEmployeeRole(EmployeeRole.valueOf(resultSet.getString("Role")));
        employee.setEmployeeStatus(EmployeeStatus.valueOf(resultSet.getString("Status")));
        employee.setCreateDate(resultSet.getTimestamp("create_date").toLocalDateTime());

        return employee;
    }
}
