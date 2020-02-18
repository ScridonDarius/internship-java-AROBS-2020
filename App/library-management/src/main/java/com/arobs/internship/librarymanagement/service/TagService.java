package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.request.TagRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.TagResponseDTO;

public interface TagService {

    TagResponseDTO addTag(TagRegistrationDTO request);

    TagResponseDTO retrieveByTagName(String tagName);
}
