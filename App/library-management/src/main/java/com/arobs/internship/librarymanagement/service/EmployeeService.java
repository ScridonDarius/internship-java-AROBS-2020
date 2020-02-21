package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.request.EmployeeRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDTO addEmployee(EmployeeRegistrationDTO request);

    EmployeeResponseDTO retrieveByUserName(String userName);

    boolean deleteEmployee(String userName);

    List<EmployeeResponseDTO> retrieveAll();
}
