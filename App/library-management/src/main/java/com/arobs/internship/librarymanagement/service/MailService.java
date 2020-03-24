package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.response.MailResponseDTO;

public interface MailService {

    void sendEmail(MailResponseDTO mail);
}
