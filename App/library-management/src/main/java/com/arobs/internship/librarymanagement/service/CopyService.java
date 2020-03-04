package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.request.CopyRegistrationDTO;
import com.arobs.internship.librarymanagement.model.Copy;

import java.util.Set;

public interface CopyService {

    Copy saveCopy(CopyRegistrationDTO copyRegistrationDTO, String bookTitle, String bookAuthor);

    Copy findByISBN(String isbn);

    Set<Copy> findCopyByBook(String author, String title);
}
