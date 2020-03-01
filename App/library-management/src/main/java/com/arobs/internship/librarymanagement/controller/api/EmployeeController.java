package com.arobs.internship.librarymanagement.controller.api;

import com.arobs.internship.librarymanagement.controller.api.request.EmployeeRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeUpdateDTO;
import com.arobs.internship.librarymanagement.exception.InvalidEmailException;
import com.arobs.internship.librarymanagement.service.impl.EmployeeServiceImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Set;

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
        EmployeeResponseDTO employeeResponse = this.employeeService.addEmployee(request);

        return employeeResponse != null
                ? new ResponseEntity<>(employeeResponse, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/findEmployee", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeResponseDTO> retrieveByUserName(
            @RequestParam String userName) {

        EmployeeResponseDTO employeeResponseDTO = null;
        this.employeeService.retrieveByUserName(userName);

        return employeeResponseDTO != null
                ? new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK)
                : new ResponseEntity<>(new EmployeeResponseDTO(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/findEmployeeByEmail", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeResponseDTO> retrieveByEmail(
            @RequestParam String email) {
        EmployeeResponseDTO employeeResponseDTO;

        try {
            employeeResponseDTO = this.employeeService.retrieveByEmail(email);
        } catch (InvalidEmailException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Invalid email format", e);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. Got a null response");
        }

        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeResponseDTO> updatePassword(
            @RequestParam String userName,
            @RequestParam String password) {
        this.employeeService.changePassword(password, userName);
        EmployeeResponseDTO employeeResponseDTO = this.employeeService.retrieveByUserName(userName);

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
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, " Object is null");
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        EmployeeUpdateDTO employeeUpdateDTO = this.employeeService.employeeUpdate(request, userName);

        return employeeUpdateDTO != null
                ? new ResponseEntity<>(employeeUpdateDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "deleteEmployee", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> deleteEmloyee(@RequestParam String userName) {

        return new ResponseEntity<>(this.employeeService.deleteEmployee(userName), HttpStatus.OK);
    }

    @RequestMapping(value = "retrieveAll", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<EmployeeResponseDTO>> retrieveAll() {
        Set<EmployeeResponseDTO> employeeResponseDTOS = this.employeeService.retrieveAll();

        return employeeResponseDTOS != null
                ? new ResponseEntity<>(employeeResponseDTOS, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
