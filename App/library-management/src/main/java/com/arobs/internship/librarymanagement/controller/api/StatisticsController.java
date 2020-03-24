package com.arobs.internship.librarymanagement.controller.api;

import com.arobs.internship.librarymanagement.controller.api.response.EmployeeResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.StatisticsBookDTO;
import com.arobs.internship.librarymanagement.controller.api.response.StatisticsEmployeeDTO;
import com.arobs.internship.librarymanagement.service.impl.StatisticsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/statistics", produces = {MediaType.APPLICATION_JSON_VALUE})
public class StatisticsController {

    @Autowired
    private StatisticsServiceImpl statisticsService;

    @RequestMapping(value = "/topBooks", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<StatisticsBookDTO> topBooks(@RequestParam  String startDate,
                                            @RequestParam  String endTime,
                                            @RequestParam Integer limit) {
        return statisticsService.topBooksRentedInASpecifiedPeriod(startDate,endTime, limit);
    }

    @RequestMapping(value = "/lateEmployees", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<EmployeeResponseDTO>> retrieveEmployeesThatAreLate() {
        Set<EmployeeResponseDTO> employees = statisticsService.employeesThatAreLate().stream()
                .map(employee -> new EmployeeResponseDTO(employee.getId(), employee.getUserName(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getEmployeeRole(), employee.getEmployeeStatus(), employee.getCreateDate())).collect(Collectors.toSet());

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @RequestMapping(value = "/topEmployees", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<StatisticsEmployeeDTO> topEmployees(@RequestParam  String startDate,
                                                    @RequestParam  String endTime) {
        return statisticsService.topEmployeesByBooksRead(startDate, endTime);
    }

}
