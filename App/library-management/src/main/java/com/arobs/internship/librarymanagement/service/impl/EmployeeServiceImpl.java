package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.EmployeeRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.EmployeeUpdateDTO;
import com.arobs.internship.librarymanagement.exception.InvalidEmailException;
import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.model.enums.EmployeeStatus;
import com.arobs.internship.librarymanagement.repository.EmployeeRepository;
import com.arobs.internship.librarymanagement.repository.factory.RepositoryFactory;
import com.arobs.internship.librarymanagement.service.EmployeeService;
import com.arobs.internship.librarymanagement.service.converter.ListToSetConverter;
import com.arobs.internship.librarymanagement.mapperConverter.EmployeeMapperConverter;
import com.arobs.internship.librarymanagement.validation.ValidationService;
import com.arobs.internship.librarymanagement.validation.util.EmployeeValidationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private final RepositoryFactory repositoryFactory;

    public EmployeeServiceImpl(RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    @PostConstruct
    public void init() {
        RepositoryFactory factory = repositoryFactory.getInstance();
        employeeRepository = factory.getEmployeeRepository();
    }

    @Override
    @Transactional
    public Employee retrieveByUserName(String userName) {
        return ValidationService.safeGetUniqueResult(getEmployeeRepository().findByUserName(userName));
    }

    @Override
    @Transactional
    public Employee retrieveByEmail(String email) throws InvalidEmailException {
        if (!EmployeeValidationUtil.isValidEmailAddress(email)) {
            throw new InvalidEmailException(HttpStatus.NOT_ACCEPTABLE, "Invalid email format");
        }
        return ValidationService.safeGetUniqueResult(getEmployeeRepository().findByEmail(email));
    }

    @Override
    @Transactional
    public Employee save(EmployeeRegistrationDTO request) throws InvalidEmailException {
        request.setEmployeeStatus(EmployeeStatus.ACTIVE);
        request.setCreateDate(LocalDateTime.now());

        if (!EmployeeValidationUtil.isValidEmailAddress(request.getEmail())) {
            throw new InvalidEmailException(HttpStatus.NOT_ACCEPTABLE, "Invalid email format");
        }
        getEmployeeRepository().save(EmployeeMapperConverter.generateEntityFromDTORegistration(request));
        return ValidationService.safeGetUniqueResult(getEmployeeRepository().findByUserName(request.getUserName()));
    }

    @Override
    @Transactional
    public boolean delete(int id) {

        final Employee employee = ValidationService.safeGetUniqueResult(getEmployeeRepository().findById(id));
        if (!Objects.isNull(employee)) {
            getEmployeeRepository().delete(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Employee changePassword(String password, String userName) {
        if (!retrieveByUserName(userName).getPassword().equals(password)) {
            return getEmployeeRepository().updatePassword(userName, password);
        } else {
            throw new ValidationException("This passowrd was recently used");
        }
    }

    @Override
    @Transactional
    public Set<Employee> retrieveAll() {
        return ListToSetConverter.convertListToSet(getEmployeeRepository().getAll());
    }

    @Transactional
    @Override
    public Employee retrieveById(int id) {
        return ValidationService.safeGetUniqueResult(getEmployeeRepository().findById(id));
    }

    @Override
    @Transactional
    public Employee update(EmployeeUpdateDTO request, String userName) {
        return updateAllEmployee(request, userName);
    }

    private Employee updateAllEmployee(EmployeeUpdateDTO request, String userName) {
        Employee employee = ValidationService.safeGetUniqueResult(getEmployeeRepository().findByUserName(userName));
        Employee oldEmployee = employee;

        if (Objects.isNull(request)) {
            throw new NullPointerException("Null object");
        }

        if (!EmployeeValidationUtil.isValidEmailAddress(request.getEmail())) {
            try {
                throw new InvalidEmailException(HttpStatus.NOT_ACCEPTABLE, "Invalid email format");

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
            getEmployeeRepository().update(userName, employee);
        }

        return employee;
    }

    protected EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }
}