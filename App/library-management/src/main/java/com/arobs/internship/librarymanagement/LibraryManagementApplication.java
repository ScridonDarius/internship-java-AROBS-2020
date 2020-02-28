package com.arobs.internship.librarymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude= HibernateJpaAutoConfiguration.class)
public class LibraryManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementApplication.class, args);
//       ApplicationContext applicationContext = SpringApplication.run(LibraryManagementApplication.class, args);
//       EmployeeRepository employeeRepository = (EmployeeRepository) applicationContext.getBean("repo");
//       LocalDateTime now = LocalDateTime.now();
//       Employee employee = new Employee("Darius","Darius","Scridon","darius","scridondarius@gmail.com", EmployeeRole.EMPLOYEE, EmployeeStatus.ACTIVE,now);
//       System.out.println(employeeRepository.findEmployee("Darius"));
    }
}
