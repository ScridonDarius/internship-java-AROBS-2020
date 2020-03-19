package com.arobs.internship.librarymanagement.scheduler;

import com.arobs.internship.librarymanagement.controller.api.request.BookRentUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.MailResponseDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.model.BookRent;
import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.model.enums.BookRentStatus;
import com.arobs.internship.librarymanagement.model.enums.EmployeeStatus;
import com.arobs.internship.librarymanagement.service.BookRentService;
import com.arobs.internship.librarymanagement.service.EmployeeService;
import com.arobs.internship.librarymanagement.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Set;

@Component
public class EmployeeSchedular {

    private static final Logger LOG = LoggerFactory.getLogger(BookRentScheduler.class);

    private static final long MILLIS_PER_MINUTE = 60000;
    private static final long DAY = 1440;

    private static final String SENDER = "scridondarius255@gmail.com";
    private static final String MAIL_SUBJECT = "Book is outdated";
    private static final String MAIL_CONTENT = "Please return book, rent is outdated";

    @Autowired
    EmployeeService employeeService;

    @Autowired
    MailService mailService;

    @Autowired
    public JavaMailSender emailSender;


    //@Scheduled(fixedRate = 15000)
    public void checkEmployeeStatus(){
        final Set<Employee> employees = getEmployeeService().retrieveAll();

        if (CollectionUtils.isEmpty(employees)) {
            return;
        }

        employees.forEach(employee -> {
           if (employee.getEmployeeStatus().equals(EmployeeStatus.SUSPENDED) &&  LocalDateTime.now().compareTo(employee.getRemovalSuspended()) > 0) {
                   employeeService.updateRemovalSuspendedDate(null, employee.getId());
                    employeeService.updateStatus(EmployeeStatus.ACTIVE, employee.getId());



                MailResponseDTO mail = new MailResponseDTO();

                mail.setMailFrom(SENDER);
                mail.setMailTo(employee.getEmail());
                mail.setMailSubject(MAIL_SUBJECT);
                mail.setMailContent(MAIL_CONTENT);
             //   mailService.sendEmail(mail);
                //TODO : uncomment all is good


            }
        });
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }
}
