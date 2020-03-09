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
import com.arobs.internship.librarymanagement.service.mapperConverter.BookRentMapperConverter;
import com.arobs.internship.librarymanagement.service.mapperConverter.CopyMapperConverter;
import com.arobs.internship.librarymanagement.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
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

        Set<Copy> copys = copyService.retrieveCopysByStatusAndBookId(bookRentRegistration.getBookId(), CopyStatus.AVAILABLE);

        if (copys.size() > 0) {
            Copy copy = copys.iterator().next();

            bookRentRegistration.setBookRentStatus(BookRentStatus.ON_GOING);
            bookRentRegistration.setRentalDate(LocalDateTime.now());
            bookRentRegistration.setReturnDate(LocalDateTime.now().plusMonths(1));
            bookRentRegistration.setCopyId(copy.getId());

            BookRent bookRent = getBookRentRepository().save(BookRentMapperConverter.generateEntityFromRegistration(bookRentRegistration));
            changeCopyStatusRented(copy.getId());

            return bookRent;
        } else {
            throw new ValidationException("No Book Available");
        }
    }

    private void changeCopyStatusRented(int copyId) {
        CopyUpdateDTO copyUpdateDTO = CopyMapperConverter.generateUpdateDTOeFromEntity(copyService.findById(copyId));
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
    public boolean delete(int bookRentId) {
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
        return null;
    }

    @Transactional
    @Override
    public BookRent update(BookRentUpdateDTO bookRentUpdateDTO, int bookRentId) throws FoundException {
        return null;
    }

    public BookRentRepository getBookRentRepository() {
        return bookRentRepository;
    }
}
