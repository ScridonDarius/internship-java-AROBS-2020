package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.CopyRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.CopyUpdateDTO;
import com.arobs.internship.librarymanagement.model.Book;
import com.arobs.internship.librarymanagement.model.Copy;
import com.arobs.internship.librarymanagement.model.enums.CopyCondition;
import com.arobs.internship.librarymanagement.model.enums.CopyStatus;
import com.arobs.internship.librarymanagement.repository.CopyRepository;
import com.arobs.internship.librarymanagement.repository.factory.RepositoryFactory;
import com.arobs.internship.librarymanagement.service.BookService;
import com.arobs.internship.librarymanagement.service.CopyService;
import com.arobs.internship.librarymanagement.service.converter.ListToSetConverter;
import com.arobs.internship.librarymanagement.service.mapperConverter.CopyMapperConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Set;

@Service
public class CopyServiceImpl implements CopyService {

    @Autowired
    private CopyRepository copyRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private  RepositoryFactory repositoryFactory;

    @PostConstruct
    public void init() {
        RepositoryFactory factory = repositoryFactory.getInstance();
        copyRepository = factory.getCopyRepository();
    }
    @Transactional
    @Override
    public Set<Copy> findCopyByAuthorAndTitle(String author, String title) {
        Book book = bookService.retrieveBookByAuthorAndTitle(author, title);
        return ListToSetConverter.convertListToSet(copyRepository.findByBookId(book.getId()));
    }

    @Transactional
    @Override
    public Copy saveCopyByAuthorAndTitle(CopyRegistrationDTO copyRegistrationDTO, String bookTitle, String bookAuthor) {
        Copy copy = save(copyRegistrationDTO);
        copy.setBook(bookService.retrieveBookByAuthorAndTitle(bookAuthor, bookTitle));

        return copyRepository.save(copy);
    }

    @Transactional
    @Override
    public Copy saveCopyByBookId(CopyRegistrationDTO copyRegistrationDTO, int bookId) {
        Copy copy = save(copyRegistrationDTO);
        copy.setBook(bookService.retrieveBookById(bookId));
        return copyRepository.save(copy);
    }

    @Override
    @Transactional
    public Set<Copy> retrieveCopysByStatusAndBookId(int bookId, CopyStatus copyStatus){
        return ListToSetConverter.convertListToSet(getCopyRepository().findCopysByStatusAndByBookId(bookId, copyStatus.toString()));
    }

    @Transactional
    @Override
    public Set<Copy> findAll() {
        return ListToSetConverter.convertListToSet(getCopyRepository().getAll());
    }

    @Transactional
    @Override
    public Copy findByISBN(String isbn) {
        return copyRepository.findByISBN(isbn);
    }

    @Transactional
    @Override
    public Copy findById(int copyId) {
        return copyRepository.findById(copyId);
    }

    @Transactional
    @Override
    public Set<Copy> findCopyByBookId(int bookId) {
        return ListToSetConverter.convertListToSet(copyRepository.findByBookId(bookId));
    }

    @Transactional
    @Override
    public boolean delete(int copyId) {
        final Copy copy = getCopyRepository().findById(copyId);
        if (!Objects.isNull(copy)) {
            getCopyRepository().deleteCopy(copy);
            return true;
        }
        return false;

    }

    @Transactional
    @Override
    public Copy update(CopyUpdateDTO copyUpdateDTO, int copyId) {
        final Copy copy = findById(copyId);
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

    protected CopyRepository getCopyRepository() {
        return copyRepository;
    }
}
