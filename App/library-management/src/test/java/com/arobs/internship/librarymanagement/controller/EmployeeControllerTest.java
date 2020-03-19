package com.arobs.internship.librarymanagement.controller;

import com.arobs.internship.librarymanagement.controller.api.EmployeeController;
import com.arobs.internship.librarymanagement.controller.api.request.EmployeeRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.EmployeeUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeResponseDTO;
import com.arobs.internship.librarymanagement.exception.InvalidEmailException;
import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.model.enums.EmployeeRole;
import com.arobs.internship.librarymanagement.model.enums.EmployeeStatus;
import com.arobs.internship.librarymanagement.service.EmployeeService;
import com.arobs.internship.librarymanagement.mapperConverter.EmployeeMapperConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    static Set<Employee> employees = new HashSet<>();

    private EmployeeRegistrationDTO employeeRegistrationDTO = new EmployeeRegistrationDTO("scridondarius", "Darius", "idon", "arius", "scridondariu", EmployeeRole.EMPLOYEE, EmployeeStatus.ACTIVE, LocalDateTime.now());
    private EmployeeUpdateDTO employeeUpdateDTO = new EmployeeUpdateDTO("darius", "darius", "dariusscridon25@gmail.com");

    @BeforeAll
    static void setUp() {
        employees.add(new Employee(1, "scridondarius", "Darius", "Scridon", "scridondarius", "scridondarius@gmail.com", EmployeeRole.EMPLOYEE, EmployeeStatus.ACTIVE,LocalDateTime.now(), LocalDateTime.now()));
        employees.add(new Employee(2, "mariusscridon", "Marius", "Scridon", "scridonmarius", "scridonmarius@gmail.com", EmployeeRole.EMPLOYEE, EmployeeStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now().minusMonths(1)));
    }

    @Test
    void given_whenRetrieveAll_returnResponseEntity() {
        when(employeeService.retrieveAll()).thenReturn(employees);
        ResponseEntity responseEntity = employeeController.retrieveAll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void given_whenRetrieveAll_returnSetSize() {
        when(employeeService.retrieveAll()).thenReturn(employees);
        ResponseEntity responseEntity = employeeController.retrieveAll();
        Set<EmployeeResponseDTO> employeeResponseDTO = (Set<EmployeeResponseDTO>) responseEntity.getBody();
        assertEquals(2, employeeResponseDTO.size());
    }

    @Test
    void givenEmployeeUserName_whenRetrieveByUserName_returnResponseEntity() {
        when(employeeService.retrieveByUserName("scridondarius")).thenReturn(employees.iterator().next());
        ResponseEntity responseEntity = employeeController.retrieveByUserName("scridondarius");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void givenInvalidEmployeeUserName_whenRetrieveByUserName_returnException() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            employeeController.retrieveByUserName("dadada");
        });
    }

    @Test
    void givenEmployeeUserName_whenRetrieveByUserName_returnEmployeeResponseDTO() {
        when(employeeService.retrieveByUserName("scridondarius")).thenReturn(employees.iterator().next());
        ResponseEntity responseEntity = employeeController.retrieveByUserName("scridondarius");
        EmployeeResponseDTO expectedEmployee = EmployeeMapperConverter.generateDTOResponseFromEntity(employees.iterator().next());
        EmployeeResponseDTO actualEmployee = (EmployeeResponseDTO) responseEntity.getBody();
        assertEquals(expectedEmployee.toString(), actualEmployee.toString());
    }

    @Test
    void givenEmployeeId_whenDelete_returnResponseEntity() {
        ResponseEntity responseEntity = employeeController.delete(1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void givenInvalidEmployeeId_whenDelete_returnResponseEntity() {
        ResponseEntity responseEntity = employeeController.delete(1);
        assertEquals(false, responseEntity.getBody());
    }

    @Test
    void givenRegistrationDTO_whenCreateEmployee_returnException() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            employeeController.create(employeeRegistrationDTO);
        });
    }

    @Test
    void givenRegistrationDTO_whenCreateEmployee_returnResponseEntity() throws InvalidEmailException {
        when(employeeService.save(employeeRegistrationDTO)).thenReturn(EmployeeMapperConverter.generateEntityFromDTORegistration(employeeRegistrationDTO));
        ResponseEntity responseEntity = employeeController.create(employeeRegistrationDTO);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void givenEmployeeEmail_whenRetrieveByEmail_returnResponseEntity() throws InvalidEmailException {
        when(employeeService.retrieveByEmail("scridondarius@gmail.com")).thenReturn(employees.iterator().next());
        ResponseEntity responseEntity = employeeController.retrieveByEmail("scridondarius@gmail.com");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void givenEmployeeEmail_whenRetrieveByEmail_returnException() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            employeeController.retrieveByEmail("scridondarius25@gmail.com");
        });
    }

    @Test
    void givenEmployeeId_whenRetrieveById_returnResponseEntity() {
        when(employeeService.retrieveById(1)).thenReturn(employees.iterator().next());
        ResponseEntity responseEntity = employeeController.retrieveById(1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void givenEmployeeId_whenRetrieveById_returnException() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            employeeController.retrieveById(1);
        });
    }

    @Test
    void givenEmployeeUserNameAndUpdateDTO_whenUpdate_returnResponseEntity() {

        // TODO : repair method :)

        when(employeeService.update(employeeUpdateDTO, "darius")).thenReturn(employees.iterator().next());
        ResponseEntity responseEntity = employeeController.update("darius", employeeUpdateDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
