package com.arobs.internship.librarymanagement.controller.api;

import com.arobs.internship.librarymanagement.controller.api.request.EmployeeRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.TagResponseDTO;
import com.arobs.internship.librarymanagement.exception.InvalidEmailException;
import com.arobs.internship.librarymanagement.service.impl.EmployeeServiceImpl;
import com.arobs.internship.librarymanagement.service.mapperConverter.EmployeeMapperConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/employee", produces = {MediaType.APPLICATION_JSON_VALUE})
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeResponseDTO> createEmployee(
            @RequestBody EmployeeRegistrationDTO request) {
        EmployeeResponseDTO employeeResponse;

        try {
           employeeResponse = EmployeeMapperConverter.generateDTOResponseFromEntity(getEmployeeService().addEmployee(request));
        } catch (InvalidEmailException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Invalid email format", e);
        }

        return employeeResponse != null
                ? new ResponseEntity<>(employeeResponse, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "retrieveByUserName", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeResponseDTO> retrieveByUserName(
            @RequestParam String userName) {

        EmployeeResponseDTO employeeResponseDTO = EmployeeMapperConverter.generateDTOResponseFromEntity(getEmployeeService().retrieveByUserName(userName));

        return employeeResponseDTO != null
                ? new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK)
                : new ResponseEntity<>(new EmployeeResponseDTO(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "retrieveByEmail", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeResponseDTO> retrieveByEmail(
            @RequestParam String email) {
        EmployeeResponseDTO employeeResponseDTO;

        try {
            employeeResponseDTO = EmployeeMapperConverter.generateDTOResponseFromEntity(getEmployeeService().retrieveByEmail(email));
        } catch (InvalidEmailException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Invalid email format", e);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This email doesn't exist!");
        }

        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "updatePassword", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeResponseDTO> updatePassword(
            @RequestParam String userName,
            @RequestParam String password) {
        this.employeeService.changePassword(password, userName);
        EmployeeResponseDTO employeeResponseDTO = EmployeeMapperConverter.generateDTOResponseFromEntity(getEmployeeService().retrieveByUserName(userName));

        return employeeResponseDTO != null
                ? new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/updateEmployee", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeUpdateDTO> updateEmployee(
            @RequestParam String userName,
            @RequestBody @Valid EmployeeUpdateDTO request) {

        if (Objects.isNull(request)) {
            try {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, " Inexistent Employee");
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        EmployeeUpdateDTO employeeUpdateDTO = EmployeeMapperConverter.generateDTOUpdateFromEntity(getEmployeeService().employeeUpdate(request, userName));

        return employeeUpdateDTO != null
                ? new ResponseEntity<>(employeeUpdateDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> deleteEmloyee(@RequestParam String userName) {

        return new ResponseEntity<>(this.employeeService.deleteEmployee(userName), HttpStatus.OK);
    }

    @RequestMapping(value = "/retrieveEmployees", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<EmployeeResponseDTO>> retrieveAll() {
        Set<EmployeeResponseDTO> emloyee = getEmployeeService().retrieveAll().stream().map(employee -> new EmployeeResponseDTO(employee.getId(), employee.getUserName(), employee.getFirstName(),employee.getLastName(), employee.getEmail(), employee.getEmployeeRole(), employee.getEmployeeStatus(), employee.getCreateDate())).collect(Collectors.toSet());


        return emloyee != null
                ? new ResponseEntity<>(emloyee, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public EmployeeServiceImpl getEmployeeService() {
        return employeeService;
    }
}
