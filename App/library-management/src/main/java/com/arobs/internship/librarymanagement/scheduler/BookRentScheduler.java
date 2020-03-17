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
    private static final long MINUTE = 30;

    private static final String SENDER = "scridondarius255@gmail.com";
    private static final String MAIL_SUBJECT = "Book is outdated";
    private static final String MAIL_CONTENT = "Please return book, rent is outdated";

    @Autowired
    private BookRentService bookRentService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    MailService mailService;

    @Autowired
    public JavaMailSender emailSender;


   // @Scheduled(fixedRate = MINUTE * MILLIS_PER_MINUTE)
    public void checkRentTime() throws FoundException {
        final Set<BookRent> bookRents = getBookRentService().getBookRentsOrderedByDate();

        if (CollectionUtils.isEmpty(bookRents)) {
            return;
        }

        bookRents.forEach(bookRent -> {
            if (bookRent.getRentalDate().plusMonths(1).compareTo(LocalDateTime.now()) < 0 && bookRent.getBookRentStatus() != BookRentStatus.LATE) {
                bookRent.setBookRentStatus(BookRentStatus.LATE);

                BookRentUpdateDTO bookRentUpdateDTO = new BookRentUpdateDTO();
                bookRentUpdateDTO.setBookRentStatus(BookRentStatus.LATE);

                Employee employee = employeeService.retrieveById(bookRent.getEmployee().getId());
                MailResponseDTO mail = new MailResponseDTO();

                mail.setMailFrom(SENDER);
                mail.setMailTo(employee.getEmail());
                mail.setMailSubject(MAIL_SUBJECT);
                mail.setMailContent(MAIL_CONTENT);
                //  mailService.sendEmail(mail); TODO : uncomment all is good

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
