package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.controller.api.request.TagRegistrationDTO;
import com.arobs.internship.librarymanagement.model.Tag;

import java.util.Set;

public interface TagService {

    Tag addTag(TagRegistrationDTO request);

    Tag retrieveByTagName(String tagName);

    Tag updateTag(String tagName, String newTag);

    boolean deleteTag(String tagName);

    Set<Tag> getAll();
}
