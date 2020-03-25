package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.BookRentRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.BookRentUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.MailResponseDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.model.*;
import com.arobs.internship.librarymanagement.model.enums.*;
import com.arobs.internship.librarymanagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    private static final String SENDER = "scridondarius255@gmail.com";
    private static final String MAIL_SUBJECT_COPY_AVAILABILITY = "A new copy for book you rented is available";
    private static final String MAIL_CONTENT_COPY_AVAILABILITY = "Please response With ACCEPT in case you want to rent book otherwise DECLINE";

    private static final String MAIL_SUBJECT_SUSPENDED = "Your Account is ACTIVE";
    private static final String MAIL_CONTENT_SUSPENDED = "Your account is active, you can rent book again!";

    private static final String MAIL_SUBJECT_EMPLOYEE = "Your Account is ACTIVE";
    private static final String MAIL_CONTENT_EMPLOYEE = "Your account is active, you can rent book again!";

    @Autowired
    private CopyService copyService;

    @Autowired
    private RentRequestConfirmationService rentRequestConfirmationService;

    @Autowired
    private RentRequestService rentRequestService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MailService mailService;

    @Autowired
    private BookRentService bookRentService;

    @Transactional
    @Override
    public void checkCopyAvailability() {
        Set<RentRequest> rentRequests = rentRequestService.getRentRequestsOrderByDate();

        rentRequests.forEach(rentRequest -> {

            Set<Copy> copiesByStatus = copyService.retrieveByStatusAndBookId(rentRequest.getBook().getId(), CopyStatus.AVAILABLE);
            if (!CollectionUtils.isEmpty(copiesByStatus)) {
                Copy copy = copiesByStatus.iterator().next();
                RentRequest request = rentRequestService.retrieveById(rentRequest.getId());
                Employee employee = employeeService.retrieveById(rentRequest.getEmployee().getId());

                rentRequestConfirmationService.save(rentRequestConfirmation(rentRequest, copy));
                copy.setCopyStatus(CopyStatus.PENDING);
                request.setRentRequestStatus(RentRequestStatus.WAITING_CONFIRMATION);

                MailResponseDTO mail = new MailResponseDTO();
                mail.setMailSubject(MAIL_SUBJECT_COPY_AVAILABILITY);
                mail.setMailContent(MAIL_CONTENT_COPY_AVAILABILITY);

                sendEmail(employee.getEmail(), mail);
            }
        });
    }

    private RentRequestConfirmation rentRequestConfirmation(RentRequest rentRequest, Copy copy) {
        RentRequestConfirmation rentRequestConf = new RentRequestConfirmation();
        rentRequestConf.setRentRequestId(rentRequest);
        rentRequestConf.setCopyId(copy);
        rentRequestConf.setConfirmation(LocalDateTime.now());
        rentRequestConf.setRentRequestConfirmationStatus(RentRequestConfirmationStatus.ACTIVE);

        return rentRequestConf;
    }

    @Transactional
    @Override
    public void emailDatePassed() {
        List<RentRequestConfirmation> rentRequestConfirmations = rentRequestConfirmationService.orderByConfirmationDate();

        rentRequestConfirmations.forEach(rentRequestConfirmation -> {
            RentRequest rentRequest = rentRequestService.retrieveById(rentRequestConfirmation.getRentRequestId().getId());

            if (rentRequest.getRentRequestStatus().equals(RentRequestStatus.DECLINE)) {
            }

            if ((LocalDateTime.now().compareTo(rentRequestConfirmation.getConfirmation().plusDays(1)) > 0) && rentRequest.getRentRequestStatus().equals(RentRequestStatus.WAITING_CONFIRMATION)) {

                rentRequest.setRentRequestStatus(RentRequestStatus.DECLINE);
                rentRequestService.update(rentRequest);

                rentRequestConfirmation.setRentRequestConfirmationStatus(RentRequestConfirmationStatus.INACTIVE);
                rentRequestConfirmationService.update(rentRequestConfirmation);
            }

            List<BookRent> bookRents = bookRentService.retrieveByBookIdAndEmployeeId(rentRequest.getBook().getId(), rentRequest.getEmployee().getId());
            if (LocalDateTime.now().compareTo(rentRequestConfirmation.getConfirmation().plusDays(1)) < 0 && rentRequest.getRentRequestStatus().equals(RentRequestStatus.GRANTED) && rentRequestConfirmation.getRentRequestConfirmationStatus().equals(RentRequestConfirmationStatus.ACTIVE) && CollectionUtils.isEmpty(bookRents)) {
                Copy copy = copyService.retrieveById(rentRequestConfirmation.getCopyId().getId());
                copy.setCopyStatus(CopyStatus.RENT);

                bookRentService.saveBookRent(createBookRentDTO(rentRequest, rentRequestConfirmation, BookRentStatus.ON_GOING));
                rentRequestConfirmationService.updateStatus(RentRequestConfirmationStatus.INACTIVE, rentRequestConfirmation.getId());
            }
        });
    }

    private BookRentRegistrationDTO createBookRentDTO(RentRequest rentRequest, RentRequestConfirmation rentRequestConfirmation, BookRentStatus bookRentStatus) {
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

    @Override
    @Transactional
    public void employeeStatus() {
        final Set<Employee> employees = employeeService.retrieveAll();

        if (CollectionUtils.isEmpty(employees)) {
            return;
        }
        employees.forEach(employee -> {
            if (employee.getEmployeeStatus().equals(EmployeeStatus.SUSPENDED) && LocalDateTime.now().compareTo(employee.getRemovalSuspended()) > 0) {
                employeeService.updateRemovalSuspendedDate(null, employee.getId());
                employeeService.updateStatus(EmployeeStatus.ACTIVE, employee.getId());

                MailResponseDTO mail = new MailResponseDTO();
                mail.setMailSubject(MAIL_SUBJECT_SUSPENDED);
                mail.setMailContent(MAIL_CONTENT_SUSPENDED);

                sendEmail(employee.getEmail(), mail);
            }
        });
    }

    private void sendEmail(String email, MailResponseDTO mail) {

        mail.setMailFrom(SENDER);
        mail.setMailTo(email);
        mailService.sendEmail(mail);
    }

    @Override
    @Transactional
    public void rentTime() {
        final Set<BookRent> bookRents = bookRentService.getBookRentsOrderedByDate();

        if (CollectionUtils.isEmpty(bookRents)) {
            return;
        }

        bookRents.forEach(bookRent -> {
            if (bookRent.getReturnDate().compareTo(LocalDateTime.now()) < 0 && bookRent.getBookRentStatus() != BookRentStatus.LATE) {

                Employee employee = employeeService.retrieveById(bookRent.getEmployee().getId());
                BookRent rent = bookRentService.retrieveById(bookRent.getId());
                rent.setBookRentStatus(BookRentStatus.LATE);

                MailResponseDTO mail = new MailResponseDTO();
                mail.setMailSubject(MAIL_SUBJECT_EMPLOYEE);
                mail.setMailContent(MAIL_CONTENT_EMPLOYEE);

                sendEmail(employee.getEmail(), mail);
            }
        });
    }

}
