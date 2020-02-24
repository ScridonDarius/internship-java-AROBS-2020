package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.request.EmployeeRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeUpdateDTO;

import java.util.List;
import java.util.Set;

public interface EmployeeService {

    EmployeeResponseDTO addEmployee(EmployeeRegistrationDTO request);

    EmployeeResponseDTO retrieveByUserName(String userName);

    boolean deleteEmployee(String userName);

    Set<EmployeeResponseDTO> retrieveAll();

    EmployeeUpdateDTO employeeUpdate(EmployeeUpdateDTO request, String userName);
}
