package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.CopyRegistrationDTO;
import com.arobs.internship.librarymanagement.model.Book;
import com.arobs.internship.librarymanagement.model.Copy;
import com.arobs.internship.librarymanagement.model.enums.CopyCondition;
import com.arobs.internship.librarymanagement.model.enums.CopyStatus;
import com.arobs.internship.librarymanagement.repository.CopyRepository;
import com.arobs.internship.librarymanagement.service.BookService;
import com.arobs.internship.librarymanagement.service.CopyService;
import com.arobs.internship.librarymanagement.service.converter.ListToSetConverter;
import com.arobs.internship.librarymanagement.service.mapperConverter.CopyMapperConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class CopyServiceImpl implements CopyService {

    final CopyRepository copyRepository;

    final BookService bookService;

    public CopyServiceImpl(CopyRepository copyRepository, BookService bookService) {
        this.copyRepository = copyRepository;
        this.bookService = bookService;
    }

    @Transactional
    @Override
    public Set<Copy> findCopyByBook(String author, String title) {
        Book book = bookService.retrieveBookByAuthorAndTitle(author, title);
        return ListToSetConverter.convertListToSet(copyRepository.findByBookId(book.getId()));
    }

    @Transactional
    @Override
    public Copy saveCopy(CopyRegistrationDTO copyRegistrationDTO, String bookTitle, String bookAuthor) {
        Copy copy = CopyMapperConvertor.generateEntityFromDTORegistration(copyRegistrationDTO);
        copy.setCopyCondition(CopyCondition.GOOD);
        copy.setCopyStatus(CopyStatus.AVAILABLE);

        copy.setBook(bookService.retrieveBookByAuthorAndTitle(bookAuthor, bookTitle));
        copy.setIsbn(copyRegistrationDTO.getIsbn());


        return copyRepository.save(copy);
    }

    @Override
    public Copy findByISBN(String isbn) {
        return copyRepository.findByISBN(isbn);
    }
}
