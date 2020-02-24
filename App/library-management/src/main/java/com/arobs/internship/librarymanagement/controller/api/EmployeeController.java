package com.arobs.internship.librarymanagement.controller.api;

import com.arobs.internship.librarymanagement.controller.api.request.EmployeeRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.TagUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.EmployeeUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.TagResponseDTO;
import com.arobs.internship.librarymanagement.service.impl.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

        EmployeeResponseDTO employeeResponseDTO =
                this.employeeService.retrieveByUserName(userName);

        return employeeResponseDTO != null
                ? new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK)
                : new ResponseEntity<>(new EmployeeResponseDTO(), HttpStatus.NOT_FOUND);
    }

    //TODO : UpdateEmployee Create method in repository and service (for some fields)

//    @RequestMapping(value = "/updateEmployee", method = RequestMethod.PATCH)
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<EmployeeUpdateDTO> updateTag(
//            @RequestParam String tagName,
//            @RequestParam String newTag) {
//
//        EmployeeUpdateDTO employeeUpdateDTO = this.employeeService.update();
//        return employeeUpdateDTO != null
//                ? new ResponseEntity<>(employeeUpdateDTO, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

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
