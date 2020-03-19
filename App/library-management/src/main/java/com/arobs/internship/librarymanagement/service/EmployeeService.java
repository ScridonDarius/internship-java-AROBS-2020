package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.request.EmployeeRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.EmployeeUpdateDTO;
import com.arobs.internship.librarymanagement.exception.InvalidEmailException;
import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.model.enums.EmployeeStatus;

import java.time.LocalDateTime;
import java.util.Set;

public interface EmployeeService {

    Employee save(EmployeeRegistrationDTO request) throws InvalidEmailException;

    Employee retrieveByUserName(String userName);

    Employee retrieveByEmail(String email) throws InvalidEmailException;

    Employee retrieveById(int id);

    boolean delete(int id);

    Set<Employee> retrieveAll();

    Employee update(EmployeeUpdateDTO request, String userName);

    Employee changePassword(String password, String userName);

    void updateRemovalSuspendedDate(LocalDateTime removalSuspended, int employeeId);

    void updateStatus(EmployeeStatus employeeStatus, int employeeId);
}
