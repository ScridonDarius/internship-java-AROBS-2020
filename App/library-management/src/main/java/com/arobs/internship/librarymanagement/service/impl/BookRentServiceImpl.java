package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.BookRentRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.BookRentUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.request.CopyUpdateDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.model.BookRent;
import com.arobs.internship.librarymanagement.model.Copy;
import com.arobs.internship.librarymanagement.model.enums.BookRentStatus;
import com.arobs.internship.librarymanagement.model.enums.CopyStatus;
import com.arobs.internship.librarymanagement.repository.BookRentRepository;
import com.arobs.internship.librarymanagement.service.BookRentService;
import com.arobs.internship.librarymanagement.service.BookRequestService;
import com.arobs.internship.librarymanagement.service.CopyService;
import com.arobs.internship.librarymanagement.service.converter.ListToSetConverter;
import com.arobs.internship.librarymanagement.mapperConverter.BookRentMapperConverter;
import com.arobs.internship.librarymanagement.mapperConverter.CopyMapperConverter;
import com.arobs.internship.librarymanagement.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
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

    @Transactional
    @Override
    public BookRent save(BookRentRegistrationDTO bookRentRegistration) throws FoundException {
        final Set<Copy> copies = copyService.retrieveByStatusAndBookId(bookRentRegistration.getBookId(), CopyStatus.AVAILABLE);
        if (CollectionUtils.isEmpty(copies)) {
            throw new ValidationException();
        }

        Copy copy = copies.iterator().next();
        bookRentRegistration.setBookRentStatus(BookRentStatus.ON_GOING);
        bookRentRegistration.setRentalDate(LocalDateTime.now());
        bookRentRegistration.setReturnDate(LocalDateTime.now().plusMonths(1));
        bookRentRegistration.setCopyId(copy.getId());

        BookRent bookRent = getBookRentRepository().save(BookRentMapperConverter.generateEntityFromRegistration(bookRentRegistration));
        changeCopyStatusToRent(copy.getId());

        return bookRent;
    }

    private void changeCopyStatusToRent(int copyId) {
        CopyUpdateDTO copyUpdateDTO = CopyMapperConverter.generateUpdateDTOeFromEntity(copyService.retrieveById(copyId));
        copyUpdateDTO.setCopyStatus(CopyStatus.RENT);
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
    @Override
    public Set<BookRent> getBookRentsOrderedByDate() {
        return ListToSetConverter.convertListToSet(getBookRentRepository().orderByRentDate());
    }

    public BookRentRepository getBookRentRepository() {
        return bookRentRepository;
    }
}
