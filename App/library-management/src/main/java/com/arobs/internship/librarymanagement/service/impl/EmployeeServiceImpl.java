package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.repository.EmployeeRepository;
import com.arobs.internship.librarymanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
      this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee addEmployee(Employee request) {
        Employee employee = new Employee();

        employee.setUserName(request.getUserName().trim());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPassword(request.getPassword());
        employee.setEmployeeRole(request.getEmployeeRole());
        employee.setEmployeeStatus(request.getEmployeeStatus());
        employee.setCreateDate(request.getCreateDate());

        employeeRepository.createEmployee(employee);

        return employee;
    }
}
