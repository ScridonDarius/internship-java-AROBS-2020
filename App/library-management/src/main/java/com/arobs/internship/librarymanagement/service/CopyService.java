package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.request.CopyRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.CopyUpdateDTO;
import com.arobs.internship.librarymanagement.model.Copy;
import com.arobs.internship.librarymanagement.model.enums.CopyStatus;

import javax.transaction.Transactional;
import java.util.Set;

public interface CopyService {

    Set<Copy> retrieveByBookId(int bookId);

    Copy saveByAuthorAndTitle(CopyRegistrationDTO copyRegistrationDTO, String bookTitle, String bookAuthor);

    Copy saveByBookId(CopyRegistrationDTO copyRegistrationDTO, int bookId);

    Set<Copy> retrieveByStatusAndBookId(int bookId, CopyStatus copyStatus);

    Set<Copy> retrieveAll();

    Copy retrieveByISBN(String isbn);

    boolean delete(int copyId);

    Set<Copy> retrieveByAuthorAndTitle(String author, String title);

    Copy retrieveById(int copyId);

    Copy update(CopyUpdateDTO copyUpdateDTO, int copyId);

    Set<Copy> retreiveAllByStatus(CopyStatus copyStatus);
}
