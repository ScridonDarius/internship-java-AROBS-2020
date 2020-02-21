package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.EmployeeRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeResponseDTO;
import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.model.enums.EmployeeStatus;
import com.arobs.internship.librarymanagement.repository.EmployeeRepository;
import com.arobs.internship.librarymanagement.service.EmployeeService;
import com.arobs.internship.librarymanagement.service.mapperConverter.EmployeeMapperConverter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponseDTO retrieveByUserName(String userName) {
        return EmployeeMapperConverter.generateDTOResponseFromEntity(employeeRepository.findEmployee(userName));
    }

    @Override
    public EmployeeResponseDTO addEmployee(EmployeeRegistrationDTO request) {
        request.setEmployeeStatus(EmployeeStatus.ACTIVE);
        request.setCreateDate(LocalDateTime.now());
        employeeRepository.createEmployee(EmployeeMapperConverter.generateEntityFromDTORegistration(request));
        return EmployeeMapperConverter.generateDTOResponseFromEntity(employeeRepository.findEmployee(request.getUserName()));
    }

    @Override
    public boolean deleteEmployee(String userName) {
        return employeeRepository.deleteEmployee(userName);
    }

    @Override
    public List<EmployeeResponseDTO> retrieveAll() {
        List<EmployeeResponseDTO> employeeResponseDTOS = new ArrayList<>();
        List<Employee> employees = this.employeeRepository.findAll();

        for (Employee employeeAux : employees) {
            employeeResponseDTOS.add(EmployeeMapperConverter.generateDTOResponseFromEntity(employeeAux));
        }
        return employeeResponseDTOS;
    }
}
