package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.EmployeeRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeResponseDTO;
import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.model.enums.EmployeeStatus;
import com.arobs.internship.librarymanagement.repository.EmployeeRepository;
import com.arobs.internship.librarymanagement.service.EmployeeService;
import com.arobs.internship.librarymanagement.service.builder.EmployeeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public EmployeeResponseDTO retrieveByUserName(String userName) {
        return EmployeeBuilder.generateDTOResponseFromEntity(employeeRepository.findEmployee(userName));
    }

    @Override
    public EmployeeResponseDTO addEmployee(EmployeeRegistrationDTO request) {
        LocalDateTime now = LocalDateTime.now();
        request.setEmployeeStatus(EmployeeStatus.ACTIVE);
        request.setCreateDate(now);
        employeeRepository.createEmployee(EmployeeBuilder.generateEntityFromDTORegistration(request));
        return EmployeeBuilder.generateDTOResponseFromEntity(employeeRepository.findEmployee(request.getUserName()));
    }
}
