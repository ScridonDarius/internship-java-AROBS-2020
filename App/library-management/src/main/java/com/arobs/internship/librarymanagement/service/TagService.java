package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.request.TagRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.TagUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.TagResponseDTO;

import java.util.List;

public interface TagService {

    TagResponseDTO addTag(TagRegistrationDTO request);

    TagResponseDTO retrieveByTagName(String tagName);

    TagUpdateDTO updateTag(String tagName, String newTag);

    boolean deleteTag(String tagName);

    List<TagResponseDTO> retrieveAll();
}
