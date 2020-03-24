package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.response.StatisticsBookDTO;
import com.arobs.internship.librarymanagement.controller.api.response.StatisticsEmployeeDTO;
import com.arobs.internship.librarymanagement.model.Book;
import com.arobs.internship.librarymanagement.model.BookRent;
import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.model.enums.BookRentStatus;
import com.arobs.internship.librarymanagement.repository.StatisticsRepository;
import com.arobs.internship.librarymanagement.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private BookRentServiceImpl bookRentService;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Transactional
    public List<StatisticsBookDTO> topBooksRentedInASpecifiedPeriod(String startDate, String endTime, int limit) {

        List<StatisticsBookDTO> topBooks = new ArrayList<>();

        for (Object[] val : statisticsRepository.findBooksRented(dateConverterFromStringToLocalDateTime(startDate), dateConverterFromStringToLocalDateTime(endTime), limit)) {
            Book book = bookService.retrieveById((Integer) val[0]);
            StatisticsBookDTO statisticsBookDTO = new StatisticsBookDTO(book.getTitle(), book.getAuthor(), (BigInteger) val[1]);
            topBooks.add(statisticsBookDTO);
        }
        return topBooks;
    }

    @Transactional
    public List<StatisticsEmployeeDTO> topEmployeesByBooksRead(String startDate, String endTime) {

        List<StatisticsEmployeeDTO> topEmployees = new ArrayList<>();

        for (Object[] val : statisticsRepository.findEmployeesByBooksRead(dateConverterFromStringToLocalDateTime(startDate), dateConverterFromStringToLocalDateTime(endTime))) {

            Employee employee = employeeService.retrieveById((Integer) val[0]);
            StatisticsEmployeeDTO statisticsEmployeeDTO = new StatisticsEmployeeDTO(employee.getUserName(), (BigInteger) val[1]);
            topEmployees.add(statisticsEmployeeDTO);
        }
        return topEmployees;
    }

    @Transactional
    public Set<Employee> employeesThatAreLate() {
        Set<BookRent> bookRents = bookRentService.retrieveByStatus(BookRentStatus.LATE);
        Set<Employee> employees = new HashSet<>(bookRents.size());

        bookRents.forEach(bookRent -> employees.add(employeeService.retrieveById(bookRent.getEmployee().getId())));
        return employees;
    }

    private LocalDateTime dateConverterFromStringToLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }
}
