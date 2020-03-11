package com.arobs.internship.librarymanagement.controller;

import com.arobs.internship.librarymanagement.controller.api.EmployeeController;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeResponseDTO;
import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.model.enums.EmployeeRole;
import com.arobs.internship.librarymanagement.model.enums.EmployeeStatus;
import com.arobs.internship.librarymanagement.service.EmployeeService;
import com.arobs.internship.librarymanagement.service.mapperConverter.EmployeeMapperConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    @BeforeAll
    static void setUp() {
        employees.add(new Employee(1, "scridondarius", "Darius", "Scridon", "scridondarius", "scridondarius@gmail.com", EmployeeRole.EMPLOYEE, EmployeeStatus.ACTIVE, LocalDateTime.now()));
        employees.add(new Employee(2, "mariusscridon", "Marius", "Scridon", "scridonmarius", "scridonmarius@gmail.com", EmployeeRole.EMPLOYEE, EmployeeStatus.ACTIVE, LocalDateTime.now().minusMonths(1)));
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
    void givenEmployeeUserName_whenRetrieveByUserName_returnEmployeeResponseDTO() {
        when(employeeService.retrieveByUserName("scridondarius")).thenReturn(employees.iterator().next());
        ResponseEntity responseEntity = employeeController.retrieveByUserName("scridondarius");
        EmployeeResponseDTO expectedEmployee = EmployeeMapperConverter.generateDTOResponseFromEntity(employees.iterator().next());
        EmployeeResponseDTO actualEmployee = (EmployeeResponseDTO) responseEntity.getBody();
        assertEquals(expectedEmployee.toString(), actualEmployee.toString());
    }
}
