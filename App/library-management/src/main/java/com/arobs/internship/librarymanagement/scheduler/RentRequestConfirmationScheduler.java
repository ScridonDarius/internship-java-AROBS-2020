package com.arobs.internship.librarymanagement.scheduler;

import com.arobs.internship.librarymanagement.controller.api.request.CopyUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.MailResponseDTO;
import com.arobs.internship.librarymanagement.model.Copy;
import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.model.RentRequest;
import com.arobs.internship.librarymanagement.model.RentRequestConfirmation;
import com.arobs.internship.librarymanagement.model.enums.CopyCondition;
import com.arobs.internship.librarymanagement.model.enums.CopyStatus;
import com.arobs.internship.librarymanagement.model.enums.RentRequestStatus;
import com.arobs.internship.librarymanagement.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
public class RentRequestConfirmationScheduler {

    private static final Logger LOG = LoggerFactory.getLogger(BookRentScheduler.class);

    private static final long MILLIS_PER_MINUTE = 60000;
    private static final long MINUTE = 30;

    private static final String SENDER = "scridondarius255@gmail.com";
    private static final String MAIL_SUBJECT = "Book is outdated";
    private static final String MAIL_CONTENT = "Please return book, rent is outdated";

    @Autowired
    RentRequestConfirmationService rentRequestConfirmationService;

    @Autowired
    private BookRentService bookRentService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    MailService mailService;

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    CopyService copyService;

    @Autowired
    RentRequestService rentRequestService;


    @Scheduled(fixedRate = 15000)
    public void checkCopyAvailableAndSendMailForConfirmation() {

        Set<Copy> copies = copyService.retreiveAllByStatus(CopyStatus.AVAILABLE);
        Set<RentRequest> rentRequests = rentRequestService.getBookRentsOrderedByDate();


        rentRequests.forEach(rentRequest -> {
                    Set<Copy> copiesByStatus = copyService.retrieveByStatusAndBookId(rentRequest.getBook().getId(), CopyStatus.AVAILABLE);
                    if (!CollectionUtils.isEmpty(copiesByStatus)) {
                        Copy copy = copiesByStatus.iterator().next();

                        // Insert in intermediat table
                        RentRequestConfirmation rentRequestConfirmation = new RentRequestConfirmation();
                        rentRequestConfirmation.setRentRequestId(rentRequest);
                        rentRequestConfirmation.setCopyId(copy);
                        rentRequestConfirmation.setConfirmation(LocalDateTime.now());

                        rentRequestConfirmationService.save(rentRequestConfirmation);

                        copy.setCopyStatus(CopyStatus.PENDING);
                        CopyUpdateDTO copyUpdateDTO = new CopyUpdateDTO();
                        copyUpdateDTO.setCopyStatus(CopyStatus.PENDING);
                        copyUpdateDTO.setCopyCondition(CopyCondition.GOOD);
                        copyService.update(copyUpdateDTO, copy.getId());

                        rentRequest.setRentRequestStatus(RentRequestStatus.WAITING_CONFIRMATION);
                        rentRequestService.update(rentRequest);

                        sendEmail(rentRequest);
                    }
                }

        );
    }

    private void sendEmail(RentRequest rentRequest) {
        MailResponseDTO mail = new MailResponseDTO();
        Employee employee = employeeService.retrieveById(rentRequest.getEmployee().getId());

        mail.setMailFrom(SENDER);
        mail.setMailTo(employee.getEmail());
        mail.setMailSubject(MAIL_SUBJECT);
        mail.setMailContent(MAIL_CONTENT);
        mailService.sendEmail(mail);
    }

    public BookRentService getBookRentService() {
        return bookRentService;
    }
}