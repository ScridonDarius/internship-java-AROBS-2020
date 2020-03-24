package com.arobs.internship.librarymanagement.scheduler;

import com.arobs.internship.librarymanagement.controller.api.response.MailResponseDTO;
import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.model.RentRequest;
import com.arobs.internship.librarymanagement.model.enums.EmployeeStatus;
import com.arobs.internship.librarymanagement.service.EmployeeService;
import com.arobs.internship.librarymanagement.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Set;

@Component
public class EmployeeSchedular {

    private static final Logger LOG = LoggerFactory.getLogger(BookRentScheduler.class);

    private static final long MILLIS_PER_MINUTE = 60000;
    private static final long MINUTE = 30;

    private static final String SENDER = "scridondarius255@gmail.com";
    private static final String MAIL_SUBJECT = "Your Account is ACTIVE";
    private static final String MAIL_CONTENT = "Your account is active, you can rent book again!";

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MailService mailService;

    //@Scheduled(fixedRate = MINUTE * MILLIS_PER_MINUTE)
    public void checkEmployeeStatus() {
        final Set<Employee> employees = getEmployeeService().retrieveAll();

        if (CollectionUtils.isEmpty(employees)) {
            return;
        }
        employees.forEach(employee -> {
            if (employee.getEmployeeStatus().equals(EmployeeStatus.SUSPENDED) && LocalDateTime.now().compareTo(employee.getRemovalSuspended()) > 0) {
                employeeService.updateRemovalSuspendedDate(null, employee.getId());
                employeeService.updateStatus(EmployeeStatus.ACTIVE, employee.getId());

                sendEmail(employee.getEmail());
            }
        });
    }

    private void sendEmail(String employeeEmail) {
        MailResponseDTO mail = new MailResponseDTO();

        mail.setMailFrom(SENDER);
        mail.setMailTo(employeeEmail);
        mail.setMailSubject(MAIL_SUBJECT);
        mail.setMailContent(MAIL_CONTENT);
        mailService.sendEmail(mail);
    }

    protected EmployeeService getEmployeeService() {
        return employeeService;
    }
}
