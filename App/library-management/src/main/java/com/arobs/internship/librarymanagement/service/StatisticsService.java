package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.response.StatisticsBookDTO;
import com.arobs.internship.librarymanagement.controller.api.response.StatisticsEmployeeDTO;
import com.arobs.internship.librarymanagement.model.Employee;

import java.util.List;
import java.util.Set;

public interface StatisticsService {

    List<StatisticsBookDTO> topBooksRentedInASpecifiedPeriod(String startDate, String endTime, int limit);

    List<StatisticsEmployeeDTO> topEmployeesByBooksRead(String startDate, String endTime);

    Set<Employee> employeesThatAreLate();
}
