package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.request.CopyRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.CopyUpdateDTO;
import com.arobs.internship.librarymanagement.model.Copy;
import com.arobs.internship.librarymanagement.model.enums.CopyStatus;

import javax.transaction.Transactional;
import java.util.Set;

public interface CopyService {

    Set<Copy> findCopyByBookId(int bookId);

    Copy saveCopyByAuthorAndTitle(CopyRegistrationDTO copyRegistrationDTO, String bookTitle, String bookAuthor);

    Copy saveCopyByBookId(CopyRegistrationDTO copyRegistrationDTO, int bookId);

    @Transactional
    Set<Copy> retrieveCopysByStatusAndBookId(int bookId, CopyStatus copyStatus);

    Set<Copy> getAll();

    Copy findByISBN(String isbn);

    boolean deleteCopy(int copyId);

    Set<Copy> findCopyByAuthorAndTitle(String author, String title);

    Copy findById(int copyId);

    Copy updateCopy(CopyUpdateDTO copyUpdateDTO, int copyId);
}
