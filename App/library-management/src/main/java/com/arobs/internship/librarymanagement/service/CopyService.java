package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.request.CopyRegistrationDTO;
import com.arobs.internship.librarymanagement.model.Copy;

public interface CopyService {

    Copy saveCopy(CopyRegistrationDTO copyRegistrationDTO, String bookTitle, String bookAuthor);
}
