package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.request.EmployeeRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.EmployeeUpdateDTO;
import com.arobs.internship.librarymanagement.exception.InvalidEmailException;
import com.arobs.internship.librarymanagement.model.Employee;

import java.util.Set;

public interface EmployeeService {

    Employee addEmployee(EmployeeRegistrationDTO request) throws InvalidEmailException;

    Employee retrieveByUserName(String userName);

    Employee retrieveByEmail(String email) throws InvalidEmailException;

    boolean deleteEmployee(String userName);

    Set<Employee> retrieveAll();

    Employee employeeUpdate(EmployeeUpdateDTO request, String userName);

    Employee changePassword(String password, String userName);
}
