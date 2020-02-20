package com.arobs.internship.librarymanagement;

import com.arobs.internship.librarymanagement.config.AppConfig;
import com.arobs.internship.librarymanagement.model.Book;
import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.model.Tag;
import com.arobs.internship.librarymanagement.model.enums.EmployeeRole;
import com.arobs.internship.librarymanagement.model.enums.EmployeeStatus;
import com.arobs.internship.librarymanagement.repository.EmployeeRepository;
import com.arobs.internship.librarymanagement.repository.TagRepository;
import com.arobs.internship.librarymanagement.repository.jdbc.EmployeeRepositoryJdbcImpl;
import com.arobs.internship.librarymanagement.repository.jdbc.TagRepositoryJdbcImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;

@SpringBootApplication
public class LibraryManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementApplication.class, args);

//        ApplicationContext applicationContext = SpringApplication.run(LibraryManagementApplication.class, args);
////
//	EmployeeRepository employeeRepository = (EmployeeRepository) applicationContext.getBean("repo");
//
//        LocalDateTime now = LocalDateTime.now();
//        Employee employee = new Employee("Darius","Darius","Scridon","darius","scridondarius@gmail.com", EmployeeRole.EMPLOYEE, EmployeeStatus.ACTIVE,now);
//
//        employeeRepository.createEmployee(employee);
    }
}
