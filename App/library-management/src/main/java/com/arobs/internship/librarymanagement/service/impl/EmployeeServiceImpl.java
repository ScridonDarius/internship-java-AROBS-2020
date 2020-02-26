package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.EmployeeRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeUpdateDTO;
import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.model.enums.EmployeeStatus;
import com.arobs.internship.librarymanagement.repository.EmployeeRepository;
import com.arobs.internship.librarymanagement.repository.factory.RepositoryFactory;
import com.arobs.internship.librarymanagement.service.EmployeeService;
import com.arobs.internship.librarymanagement.service.converter.ListToSetConverter;
import com.arobs.internship.librarymanagement.service.mapperConverter.EmployeeMapperConverter;
import com.arobs.internship.librarymanagement.validation.util.EmployeeValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    private RepositoryFactory repositoryFactory;

//    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }

    @PostConstruct
    public void init() {
        RepositoryFactory factory = repositoryFactory.getInstance();
        employeeRepository = factory.getEmployeeRepository();
    }

    @Override
    public EmployeeResponseDTO retrieveByUserName(String userName) {
        return EmployeeMapperConverter.generateDTOResponseFromEntity(employeeRepository.findEmployee(userName));
    }

    @Override
    @Transactional
    public EmployeeResponseDTO addEmployee(EmployeeRegistrationDTO request) {
        request.setEmployeeStatus(EmployeeStatus.ACTIVE);
        request.setCreateDate(LocalDateTime.now());

        if (!EmployeeValidationUtil.isValidEmailAddress(request.getEmail())) {
            throw new ValidationException("Email is not correct");
        }

        employeeRepository.createEmployee(EmployeeMapperConverter.generateEntityFromDTORegistration(request));
        return EmployeeMapperConverter.generateDTOResponseFromEntity(employeeRepository.findEmployee(request.getUserName()));
    }

    @Override
    public boolean deleteEmployee(String userName) {
        return employeeRepository.deleteEmployee(userName);
    }

    @Override
    @Transactional
    public Set<EmployeeResponseDTO> retrieveAll() {
        List<EmployeeResponseDTO> employeeResponseDTOS = new ArrayList<>();
        List<Employee> employees = this.employeeRepository.findAll();

        for (Employee employeeAux : employees) {
            employeeResponseDTOS.add(EmployeeMapperConverter.generateDTOResponseFromEntity(employeeAux));
        }
        return ListToSetConverter.convertListToSet(employeeResponseDTOS);
    }

    private EmployeeUpdateDTO updateAllEmployee(EmployeeUpdateDTO request, String userName) {
        Employee employee = this.employeeRepository.findEmployee(userName);
        Employee oldEmployee = this.employeeRepository.findEmployee(userName);

        if (employee == null) {
            throw new ValidationException("Please introduce a valid userName");
        }

        if (!StringUtils.isEmpty(request.getEmail()) && !request.getEmail().equals(employee.getEmail()) && !request.getEmail().equals("string")) {
            employee.setEmail(request.getEmail().trim());
        }

        if (!StringUtils.isEmpty(request.getFirstName()) && !request.getFirstName().equals(employee.getFirstName()) && !request.getFirstName().equals("string")) {
            employee.setFirstName(request.getFirstName().trim());
        }

        if (!StringUtils.isEmpty(request.getLastName()) && !request.getLastName().equals(employee.getLastName()) && !request.getLastName().equals("string")) {
            employee.setLastName(request.getLastName().trim());
        }

        if (!oldEmployee.equals(employee)) {
            employeeRepository.updateEmployee(userName, employee);
        }

        return EmployeeMapperConverter.generateDTOUpdateFromEntity(employee);
    }

    @Override
    //@Transactional
    public EmployeeUpdateDTO employeeUpdate(EmployeeUpdateDTO request, String userName) {
        return updateAllEmployee(request, userName);
    }
}
