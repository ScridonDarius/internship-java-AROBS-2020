package com.arobs.internship.librarymanagement.scheduler;

import com.arobs.internship.librarymanagement.controller.api.request.BookRentUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.MailResponseDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.model.BookRent;
import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.model.enums.BookRentStatus;
import com.arobs.internship.librarymanagement.service.BookRentService;
import com.arobs.internship.librarymanagement.service.EmployeeService;
import com.arobs.internship.librarymanagement.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Set;


@Component
public class BookRentScheduler {

    private static final Logger LOG = LoggerFactory.getLogger(BookRentScheduler.class);

    private static final long MILLIS_PER_MINUTE = 60000;

    private static final String EMAIL_SUBJECT = "";

    @Autowired
    private BookRentService bookRentService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    MailService mailService;

    @Autowired
    public JavaMailSender emailSender;


    @Scheduled(fixedRate = 15000)
    public void checkRentTime() throws FoundException {
        final Set<BookRent> bookRents = getBookRentService().getBookRentsOrderedByDate();

        if (CollectionUtils.isEmpty(bookRents)) {
            return;
        }

        bookRents.forEach(bookRent -> {
            if (bookRent.getRentalDate().plusMinutes(3).compareTo(LocalDateTime.now()) < 0) {
                System.out.println("STATUS CHANGE");
                bookRent.setBookRentStatus(BookRentStatus.LATE);

                BookRentUpdateDTO bookRentUpdateDTO = new BookRentUpdateDTO();
                bookRentUpdateDTO.setBookRentStatus(BookRentStatus.LATE);

                Employee employee =employeeService.retrieveById(bookRent.getEmployee().getId());
                MailResponseDTO mail = new MailResponseDTO();
                mail.setMailFrom("scridondarius255@gmail.com");
                mail.setMailTo("scridonenterprise@gmail.com");
                mail.setMailSubject("BookRent it was outdated");
                mail.setMailContent("ddadad");
                mailService.sendEmail(mail);

                try {
                    getBookRentService().update(bookRentUpdateDTO, bookRent.getId());
                } catch (FoundException e) {
                    LOG.error("", e);
                }
            }
        });
    }
    
    public BookRentService getBookRentService() {
        return bookRentService;
    }
}
