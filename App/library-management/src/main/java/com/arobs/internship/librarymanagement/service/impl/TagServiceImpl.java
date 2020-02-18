package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.TagRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.TagResponseDTO;
import com.arobs.internship.librarymanagement.model.Tag;
import com.arobs.internship.librarymanagement.repository.TagRepository;
import com.arobs.internship.librarymanagement.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagRepository tagRepository;

    private static TagServiceImpl instance;

    @PostConstruct
    private void setUp() {
        instance = this;
    }

    public static final TagServiceImpl instance() {
        return instance;
    }

    @Override
    public TagResponseDTO addTag(TagRegistrationDTO request) {
        Tag tag = new Tag();

        try {
            tag.setTagName(request.getTagName().trim());
            tagRepository.createTag(tag);
            tag = tagRepository.findByTagName(tag.getTagName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new TagResponseDTO(
                tag.getId(),
                tag.getTagName());
    }

    @Override
    public TagResponseDTO retrieveByTagName(String tagName) {
        Tag tag = new Tag();

        try {
            tag = this.tagRepository.findByTagName(tagName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return
                new TagResponseDTO(
                        tag.getId(),
                        tag.getTagName());
    }
}
