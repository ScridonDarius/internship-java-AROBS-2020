package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.BookRentRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.BookRentUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.request.CopyUpdateDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.model.*;
import com.arobs.internship.librarymanagement.model.enums.BookRentStatus;
import com.arobs.internship.librarymanagement.model.enums.CopyStatus;
import com.arobs.internship.librarymanagement.model.enums.EmployeeStatus;
import com.arobs.internship.librarymanagement.repository.BookRentRepository;
import com.arobs.internship.librarymanagement.service.*;
import com.arobs.internship.librarymanagement.service.converter.ListToSetConverter;
import com.arobs.internship.librarymanagement.mapperConverter.BookRentMapperConverter;
import com.arobs.internship.librarymanagement.mapperConverter.CopyMapperConverter;
import com.arobs.internship.librarymanagement.validation.ValidationService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Set;

@Service
public class BookRentServiceImpl implements BookRentService {

    @Autowired
    BookRentRepository bookRentRepository;

    @Autowired
    BookRequestService bookRequestService;

    @Autowired
    CopyService copyService;

    @Autowired
    RentRequestService rentRequestService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    BookService bookService;

    @Transactional
    @Override
    public BookRent save(BookRentRegistrationDTO bookRentRegistration) throws FoundException {
        final Set<Copy> copies = copyService.retrieveByStatusAndBookId(bookRentRegistration.getBookId(), CopyStatus.AVAILABLE);
        Set<RentRequest> rentRequests = rentRequestService.getRentRequestsOrderByDate();
        boolean checkIfExistBookRent = getBookRentRepository().findByBookIdAndEmployeeId(bookRentRegistration.getBookId(), bookRentRegistration.getEmployeeId());

        Employee employee = employeeService.retrieveById(bookRentRegistration.getEmployeeId());

        if (employee.getEmployeeStatus().equals(EmployeeStatus.SUSPENDED)) {
            throw new ValidationException("you are suspended");
        }

        if (CollectionUtils.isEmpty(copies)) {
            throw new ValidationException("No copies avaliable");
        }

        if (checkIfExistBookRent) {
            throw new ValidationException("You have another rent for this book.");
        }

        if (!CollectionUtils.isEmpty(rentRequests)) {
            throw new ValidationException("Please make a request, someone is waiting for same book");
        }

        Copy copy = copies.iterator().next();
        bookRentRegistration.setBookRentStatus(BookRentStatus.ON_GOING);
        bookRentRegistration.setRentalDate(LocalDateTime.now());
        bookRentRegistration.setReturnDate(LocalDateTime.now().plusMonths(1));
        bookRentRegistration.setCopyId(copy.getId());

        BookRent bookRent = getBookRentRepository().save(BookRentMapperConverter.generateEntityFromRegistration(bookRentRegistration));
        changeCopyStatus(copy.getId(), CopyStatus.RENT);
        return bookRent;
    }

    private void changeCopyStatus(int copyId, CopyStatus copyStatus) {
        CopyUpdateDTO copyUpdateDTO = CopyMapperConverter.generateUpdateDTOeFromEntity(copyService.retrieveById(copyId));
        copyUpdateDTO.setCopyStatus(copyStatus);
        copyService.update(copyUpdateDTO, copyId);
    }

    @Transactional
    @Override
    public BookRent retrieveById(int rentId) {
        return ValidationService.safeGetUniqueResult(getBookRentRepository().findById(rentId));
    }

    @Transactional
    @Override
    public boolean delete(int rentId) {
        final BookRent bookRent = retrieveById(rentId);
        if (!Objects.isNull(bookRent)) {
            getBookRentRepository().delete(bookRent);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public Set<BookRent> retrieveAll() {
        return ListToSetConverter.convertListToSet(getBookRentRepository().findAll());
    }

    @Transactional
    @Override
    public Set<BookRent> retrieveByStatus(BookRentStatus bookRequestStatus) {
        return ListToSetConverter.convertListToSet(getBookRentRepository().findByStatus(bookRequestStatus.toString()));
    }

    @Transactional
    @Override
    public BookRent update(BookRentUpdateDTO bookRentUpdateDTO, int rentId) throws FoundException {
        BookRent bookRent = retrieveById(rentId);

        if (!Objects.isNull(rentId)) {
            bookRent.setBookRentStatus(bookRentUpdateDTO.getBookRentStatus());
        } else throw new FoundException();

        return bookRent;
    }

    @Transactional
    public BookRent renturnBook(int bookRentId) throws FoundException {

        BookRent bookRent = ValidationService.safeGetUniqueResult(getBookRentRepository().findById(bookRentId));

        if (bookRent.getReturnDate().compareTo(LocalDateTime.now()) < 0 && bookRent.getBookRentStatus().equals(BookRentStatus.LATE)) {
            long days = ChronoUnit.DAYS.between(bookRent.getReturnDate(), LocalDateTime.now());
            employeeService.updateRemovalSuspendedDate(LocalDateTime.now().plusDays(days * 2), bookRent.getEmployee().getId());
        }

        update(new BookRentUpdateDTO(BookRentStatus.RETURNED), bookRentId);
        Copy copy = copyService.retrieveById(bookRent.getCopy().getId());
        changeCopyStatus(copy.getId(), CopyStatus.AVAILABLE);

        return bookRent;
    }

    @Transactional
    @Override
    public Set<BookRent> getBookRentsOrderedByDate() {
        return ListToSetConverter.convertListToSet(getBookRentRepository().orderByRentDate());
    }

    public BookRentRepository getBookRentRepository() {
        return bookRentRepository;
    }
}
