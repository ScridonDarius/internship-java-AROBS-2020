package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.CopyRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.CopyUpdateDTO;
import com.arobs.internship.librarymanagement.model.Book;
import com.arobs.internship.librarymanagement.model.Copy;
import com.arobs.internship.librarymanagement.model.enums.CopyCondition;
import com.arobs.internship.librarymanagement.model.enums.CopyStatus;
import com.arobs.internship.librarymanagement.repository.CopyRepository;
import com.arobs.internship.librarymanagement.service.BookService;
import com.arobs.internship.librarymanagement.service.CopyService;
import com.arobs.internship.librarymanagement.service.converter.ListToSetConverter;
import com.arobs.internship.librarymanagement.mapperConverter.CopyMapperConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class CopyServiceImpl implements CopyService {

    @Autowired
    private CopyRepository copyRepository;

    @Autowired
    private BookService bookService;

    @Transactional
    @Override
    public Set<Copy> retrieveByAuthorAndTitle(String author, String title) {
        Book book = bookService.retrieveByAuthorAndTitle(author, title);
        return ListToSetConverter.convertListToSet(copyRepository.findByBookId(book.getId()));
    }

    @Transactional
    @Override
    public Copy saveByAuthorAndTitle(CopyRegistrationDTO copyRegistrationDTO, String bookTitle, String bookAuthor) {
        Copy copy = save(copyRegistrationDTO);
        copy.setBook(bookService.retrieveByAuthorAndTitle(bookAuthor, bookTitle));

        return copyRepository.save(copy);
    }

    @Transactional
    @Override
    public Copy saveByBookId(CopyRegistrationDTO copyRegistrationDTO, int bookId) {
        Copy copy = save(copyRegistrationDTO);
        copy.setBook(bookService.retrieveById(bookId));
        return copyRepository.save(copy);
    }

    @Override
    @Transactional
    public Set<Copy> retrieveByStatusAndBookId(int bookId, CopyStatus copyStatus) {
        return ListToSetConverter.convertListToSet(getCopyRepository().findByStatusAndByBookId(bookId, copyStatus.toString()));
    }

    @Transactional
    @Override
    public Set<Copy> retrieveAll() {
        return ListToSetConverter.convertListToSet(getCopyRepository().findAll());
    }

    @Transactional
    @Override
    public Copy retrieveByISBN(String isbn) {
        return copyRepository.findByISBN(isbn);
    }

    @Transactional
    @Override
    public Copy retrieveById(int copyId) {
        return copyRepository.findById(copyId);
    }

    @Transactional
    @Override
    public Set<Copy> retrieveByBookId(int bookId) {
        return ListToSetConverter.convertListToSet(copyRepository.findByBookId(bookId));
    }

    @Transactional
    @Override
    public boolean delete(int copyId) {
        final Copy copy = getCopyRepository().findById(copyId);
        if (copy == null) {
            return false;
        }
        getCopyRepository().delete(copy);

        return true;
    }

    @Transactional
    @Override
    public Copy update(CopyUpdateDTO copyUpdateDTO, int copyId) {
        final Copy copy = retrieveById(copyId);
        copy.setCopyStatus(copyUpdateDTO.getCopyStatus());
        copy.setCopyCondition(copyUpdateDTO.getCopyCondition());

        return copy;
    }

    private Copy save(CopyRegistrationDTO copyRegistrationDTO) {
        Copy copy = CopyMapperConverter.generateEntityFromDTORegistration(copyRegistrationDTO);
        copy.setCopyCondition(CopyCondition.GOOD);
        copy.setCopyStatus(CopyStatus.AVAILABLE);

        return copy;
    }

    @Transactional
    @Override
    public Set<Copy> retreiveAllByStatus(CopyStatus copyStatus) {
        return ListToSetConverter.convertListToSet(getCopyRepository().findAllByStatus(copyStatus.toString()));
    }

    protected CopyRepository getCopyRepository() {
        return copyRepository;
    }
}
