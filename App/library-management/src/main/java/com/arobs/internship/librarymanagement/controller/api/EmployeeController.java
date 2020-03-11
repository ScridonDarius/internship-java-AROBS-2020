package com.arobs.internship.librarymanagement.controller.api;

import com.arobs.internship.librarymanagement.controller.api.request.EmployeeRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.EmployeeUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeResponseDTO;
import com.arobs.internship.librarymanagement.exception.InvalidEmailException;
import com.arobs.internship.librarymanagement.service.EmployeeService;
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
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeResponseDTO> create(@RequestBody EmployeeRegistrationDTO request) {
        EmployeeResponseDTO employeeResponse;

        try {
            employeeResponse = EmployeeMapperConverter.generateDTOResponseFromEntity(getEmployeeService().save(request));
        } catch (InvalidEmailException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Invalid email format", e);
        }

        return employeeResponse != null
                ? new ResponseEntity<>(employeeResponse, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "retrieveByUserName", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeResponseDTO> retrieveByUserName(@RequestParam String userName) {
        EmployeeResponseDTO employeeResponseDTO = EmployeeMapperConverter.generateDTOResponseFromEntity(getEmployeeService().retrieveByUserName(userName));

        return employeeResponseDTO != null
                ? new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK)
                : new ResponseEntity<>(new EmployeeResponseDTO(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "retrieveByEmail", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeResponseDTO> retrieveByEmail(@RequestParam String email) {
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

    @RequestMapping(value = "retrieveById/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeResponseDTO> retrieveById(@PathVariable("id") Integer id) {
        EmployeeResponseDTO employeeResponseDTO;

        try {
            employeeResponseDTO = EmployeeMapperConverter.generateDTOResponseFromEntity(getEmployeeService().retrieveById(id));
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This employee doesn't exist!");
        }
        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "updatePassword", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeResponseDTO> updatePassword(@RequestParam String userName, @RequestParam String password) {
        this.employeeService.changePassword(password, userName);
        EmployeeResponseDTO employeeResponseDTO = EmployeeMapperConverter.generateDTOResponseFromEntity(getEmployeeService().changePassword(password, userName));

        return employeeResponseDTO != null
                ? new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeUpdateDTO> update(@RequestParam String userName, @RequestBody @Valid EmployeeUpdateDTO request) {

        if (Objects.isNull(request)) {
            try {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, " Inexistent Employee");
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        EmployeeUpdateDTO employeeUpdateDTO = EmployeeMapperConverter.generateDTOUpdateFromEntity(getEmployeeService().update(request, userName));

        return employeeUpdateDTO != null
                ? new ResponseEntity<>(employeeUpdateDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> delete(@RequestParam String userName) {

        return new ResponseEntity<>(this.employeeService.delete(userName), HttpStatus.OK);
    }

    @RequestMapping(value = "/retrieveAll", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<EmployeeResponseDTO>> retrieveAll() {
        Set<EmployeeResponseDTO> employees = getEmployeeService().retrieveAll().stream()
                .map(employee -> new EmployeeResponseDTO(employee.getId(), employee.getUserName(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getEmployeeRole(), employee.getEmployeeStatus(), employee.getCreateDate())).collect(Collectors.toSet());

        return employees != null
                ? new ResponseEntity<>(employees, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    protected EmployeeService getEmployeeService() {
        return employeeService;
    }
}
