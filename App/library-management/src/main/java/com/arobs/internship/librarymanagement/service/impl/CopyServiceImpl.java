package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.CopyRegistrationDTO;
import com.arobs.internship.librarymanagement.model.Book;
import com.arobs.internship.librarymanagement.model.Copy;
import com.arobs.internship.librarymanagement.model.enums.CopyCondition;
import com.arobs.internship.librarymanagement.model.enums.CopyStatus;
import com.arobs.internship.librarymanagement.repository.CopyRepository;
import com.arobs.internship.librarymanagement.service.BookService;
import com.arobs.internship.librarymanagement.service.CopyService;
import com.arobs.internship.librarymanagement.service.mapperConverter.CopyMapperConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CopyServiceImpl implements CopyService {

    @Autowired
    CopyRepository copyRepository;

    @Autowired
    BookService bookService;

    @Transactional
    @Override
    public Copy saveCopy(CopyRegistrationDTO copyRegistrationDTO, String bookTitle, String bookAuthor) {
        Copy copy = CopyMapperConvertor.generateEntityFromDTORegistration(copyRegistrationDTO);
        copy.setCopyCondition(CopyCondition.GOOD);
        copy.setCopyStatus(CopyStatus.AVAILABLE);
       // Book book =  bookService.retrieveBookByAuthorAndTitle(bookTitle, bookAuthor);
        copy.setBook(bookService.retrieveBookByAuthorAndTitle(bookAuthor, bookTitle));
        copy.setIsbn(copyRegistrationDTO.getIsbn());

        copyRepository.save(copy);
        return copy;
    }
}
