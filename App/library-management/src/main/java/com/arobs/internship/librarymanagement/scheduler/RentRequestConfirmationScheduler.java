package com.arobs.internship.librarymanagement.scheduler;

import com.arobs.internship.librarymanagement.controller.api.request.BookRentRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.CopyUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.MailResponseDTO;
import com.arobs.internship.librarymanagement.model.*;
import com.arobs.internship.librarymanagement.model.enums.*;
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
import java.util.Set;

@Component
public class RentRequestConfirmationScheduler {

    private static final Logger LOG = LoggerFactory.getLogger(BookRentScheduler.class);

    private static final long MILLIS_PER_MINUTE = 60000;
    private static final long MINUTE = 10;

    private static final String SENDER = "scridondarius255@gmail.com";
    private static final String MAIL_SUBJECT = "A new copy for book you rented is available";
    private static final String MAIL_CONTENT = "Please response With ACCEPT in case you want to rent book otherwise DECLINE";

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

    // @Scheduled(fixedRate = MINUTE * MILLIS_PER_MINUTE)
    public void checkEmailDateIsPassed() {
        List<RentRequestConfirmation> rentRequestConfirmations = rentRequestConfirmationService.orderByConfirmationDate();

        rentRequestConfirmations.forEach(rentRequestConfirmation -> {
            RentRequest rentRequest = rentRequestService.retrieveById(rentRequestConfirmation.getRentRequestId().getId());

            if (rentRequest.getRentRequestStatus().equals(RentRequestStatus.DECLINE)) {
            }

            if ((LocalDateTime.now().compareTo(rentRequestConfirmation.getConfirmation().plusDays(1)) > 0) && rentRequest.getRentRequestStatus().equals(RentRequestStatus.WAITING_CONFIRMATION)) {

                rentRequest.setRentRequestStatus(RentRequestStatus.DECLINE);
                rentRequestService.update(rentRequest);

                RentRequestConfirmation rentRequestConfirmation1 = rentRequestConfirmationService.retrieveById(rentRequestConfirmation.getId());
                rentRequestConfirmation1.setRentRequestConfirmationStatus(RentRequestConfirmationStatus.INACTIVE);
                rentRequestConfirmationService.update(rentRequestConfirmation1);
            }

            if (LocalDateTime.now().compareTo(rentRequestConfirmation.getConfirmation().plusDays(1)) < 0 && rentRequest.getRentRequestStatus().equals(RentRequestStatus.GRANTED) && rentRequestConfirmation.getRentRequestConfirmationStatus().equals(RentRequestConfirmationStatus.ACTIVE) && CollectionUtils.isEmpty(getBookRentService().retrieveByBookIdAndEmployeeId(rentRequest.getBook().getId(), rentRequest.getEmployee().getId()))) {

                changeCopyStatus(CopyStatus.RENT, rentRequestConfirmation.getCopyId().getId());

                bookRentService.saveBookRent(createBookRentDTO(rentRequest,rentRequestConfirmation, BookRentStatus.ON_GOING));
                rentRequestConfirmationService.updateStatus(RentRequestConfirmationStatus.INACTIVE, rentRequestConfirmation.getId());
            }
        });
    }

    private BookRentRegistrationDTO createBookRentDTO(RentRequest rentRequest, RentRequestConfirmation rentRequestConfirmation, BookRentStatus bookRentStatus){
        BookRentRegistrationDTO bookRentRegistrationDTO = new BookRentRegistrationDTO();
        bookRentRegistrationDTO.setBookRentStatus(BookRentStatus.ON_GOING);
        bookRentRegistrationDTO.setCopyId(rentRequestConfirmation.getCopyId().getId());
        bookRentRegistrationDTO.setRentalDate(LocalDateTime.now());
        bookRentRegistrationDTO.setReturnDate(LocalDateTime.now().plusMonths(1));
        bookRentRegistrationDTO.setBookId(rentRequest.getBook().getId());
        bookRentRegistrationDTO.setEmployeeId(rentRequest.getEmployee().getId());
        bookRentRegistrationDTO.setNote("");

        return bookRentRegistrationDTO;
    }

    // @Scheduled(fixedRate = MINUTE * MILLIS_PER_MINUTE)
    public void checkCopyAvailableAndSendMailForConfirmation() {
        Set<RentRequest> rentRequests = rentRequestService.getRentRequestsOrderByDate();

        rentRequests.forEach(rentRequest -> {
                    Set<Copy> copiesByStatus = copyService.retrieveByStatusAndBookId(rentRequest.getBook().getId(), CopyStatus.AVAILABLE);
                    if (!CollectionUtils.isEmpty(copiesByStatus)) {
                        Copy copy = copiesByStatus.iterator().next();

                        rentRequestConfirmationService.save(rentRequestConfirmation(rentRequest, copy));
                        changeCopyStatus(CopyStatus.PENDING, copy.getId());

                        rentRequest.setRentRequestStatus(RentRequestStatus.WAITING_CONFIRMATION);
                        rentRequestService.update(rentRequest);

                        sendEmail(rentRequest);
                    }
                }
        );
    }

    private RentRequestConfirmation rentRequestConfirmation(RentRequest rentRequest, Copy copy) {
        RentRequestConfirmation rentRequestConfirmation = new RentRequestConfirmation();
        rentRequestConfirmation.setRentRequestId(rentRequest);
        rentRequestConfirmation.setCopyId(copy);
        rentRequestConfirmation.setConfirmation(LocalDateTime.now());
        rentRequestConfirmation.setRentRequestConfirmationStatus(RentRequestConfirmationStatus.ACTIVE);

        return rentRequestConfirmation;
    }

    private void changeCopyStatus(CopyStatus copyStatus, int copyId) {
        CopyUpdateDTO copyUpdateDTO = new CopyUpdateDTO();
        copyUpdateDTO.setCopyStatus(copyStatus);
        copyUpdateDTO.setCopyCondition(CopyCondition.GOOD);
        copyService.update(copyUpdateDTO, copyId);
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

    protected BookRentService getBookRentService() {
        return bookRentService;
    }
}