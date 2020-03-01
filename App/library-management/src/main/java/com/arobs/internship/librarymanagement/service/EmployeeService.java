package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.request.EmployeeRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeUpdateDTO;
import com.arobs.internship.librarymanagement.exception.InvalidEmailException;

import java.util.Set;

public interface EmployeeService {

    EmployeeResponseDTO addEmployee(EmployeeRegistrationDTO request);

    EmployeeResponseDTO retrieveByUserName(String userName);

    EmployeeResponseDTO retrieveByEmail(String email) throws InvalidEmailException;

    boolean deleteEmployee(String userName);

    Set<EmployeeResponseDTO> retrieveAll();

    EmployeeUpdateDTO employeeUpdate(EmployeeUpdateDTO request, String userName);

    EmployeeResponseDTO changePassword(String password,String userName);
}
