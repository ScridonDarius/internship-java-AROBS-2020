package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.EmployeeRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeUpdateDTO;
import com.arobs.internship.librarymanagement.exception.InvalidEmailException;
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
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    private RepositoryFactory repositoryFactory;

    @PostConstruct
    public void init() {
        RepositoryFactory factory = repositoryFactory.getInstance();
        employeeRepository = factory.getEmployeeRepository();
    }

    @Override
    @Transactional
    public EmployeeResponseDTO retrieveByUserName(String userName) {
        return EmployeeMapperConverter.generateDTOResponseFromEntity(employeeRepository.findEmployee(userName));
    }

    @Override
    @Transactional
    public EmployeeResponseDTO retrieveByEmail(String email) throws InvalidEmailException {
        if (EmployeeValidationUtil.isValidEmailAddress(email) == false) {
            throw new InvalidEmailException();
        }
        return EmployeeMapperConverter.generateDTOResponseFromEntity(employeeRepository.findEmployeeByEmail(email));
    }


    @Override
    @Transactional
    public EmployeeResponseDTO addEmployee(EmployeeRegistrationDTO request) {
        request.setEmployeeStatus(EmployeeStatus.ACTIVE);
        request.setCreateDate(LocalDateTime.now());

        if (!EmployeeValidationUtil.isValidEmailAddress(request.getEmail())) {

            try {
                throw new InvalidEmailException();

            } catch (InvalidEmailException e) {
                e.printStackTrace();
            }
        }
        employeeRepository.createEmployee(EmployeeMapperConverter.generateEntityFromDTORegistration(request));
        return EmployeeMapperConverter.generateDTOResponseFromEntity(employeeRepository.findEmployee(request.getUserName()));
    }

    @Override
    @Transactional
    public boolean deleteEmployee(String userName) {

        final Employee employee = getEmployeeRepository().findEmployee(userName);
        if (Objects.isNull(employee)) {
            //TODO: throw an exception?? or return false?
        }
        employeeRepository.deleteEmployee(userName);
        return true;
    }

    @Override
    @Transactional
    public EmployeeResponseDTO changePassword(String password, String userName) {
        if (!retrieveByUserName(userName).getPassword().equals(password)) {
            return EmployeeMapperConverter.generateDTOResponseFromEntity(employeeRepository.updatePassword(userName, password));
        } else {
            throw new ValidationException("This passowrd was recently used");
        }
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

    @Override
    @Transactional
    public EmployeeUpdateDTO employeeUpdate(EmployeeUpdateDTO request, String userName) {
        return updateAllEmployee(request, userName);
    }

    private EmployeeUpdateDTO updateAllEmployee(EmployeeUpdateDTO request, String userName) {
        Employee employee = this.employeeRepository.findEmployee(userName);
        Employee oldEmployee = this.employeeRepository.findEmployee(userName);

        if (Objects.isNull(request)) {
            throw new NullPointerException("Null object");
        }

        if (!EmployeeValidationUtil.isValidEmailAddress(request.getEmail())) {

            try {
                throw new InvalidEmailException();

            } catch (InvalidEmailException e) {
                e.printStackTrace();
            }
        }

        if (!StringUtils.isEmpty(request.getEmail()) && !request.getEmail().equals(employee.getEmail())) {
            employee.setEmail(request.getEmail().trim());
        }

        if (!StringUtils.isEmpty(request.getFirstName()) && !request.getFirstName().equals(employee.getFirstName())) {
            employee.setFirstName(request.getFirstName().trim());
        }

        if (!StringUtils.isEmpty(request.getLastName()) && !request.getLastName().equals(employee.getLastName())) {
            employee.setLastName(request.getLastName().trim());
        }

        if (!oldEmployee.equals(employee) && EmployeeValidationUtil.isValidEmailAddress(employee.getEmail())) {
            employeeRepository.updateEmployee(userName, employee);
        }

        return EmployeeMapperConverter.generateDTOUpdateFromEntity(employee);
    }

    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }
}