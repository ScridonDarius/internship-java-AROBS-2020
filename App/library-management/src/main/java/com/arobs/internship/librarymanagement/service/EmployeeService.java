package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.request.EmployeeRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.TagRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.TagResponseDTO;
import com.arobs.internship.librarymanagement.model.Employee;

public interface EmployeeService {

    EmployeeResponseDTO addEmployee(EmployeeRegistrationDTO request);

    EmployeeResponseDTO retrieveByUserName(String userName);
}
