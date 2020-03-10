package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.request.TagRegistrationDTO;
import com.arobs.internship.librarymanagement.model.Tag;

import java.util.List;

public interface TagService {

    Tag save(TagRegistrationDTO request);

    Tag retrieveByTagName(String tagName);

    Tag update(String tagName, String newTag);

    boolean delete(String tagName);

    List<Tag> getAll();
}
