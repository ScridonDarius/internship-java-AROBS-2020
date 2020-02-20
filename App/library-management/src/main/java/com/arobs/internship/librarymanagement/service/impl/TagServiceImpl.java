package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.service.builder.TagBuilder;
import com.arobs.internship.librarymanagement.controller.api.request.TagRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.TagUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.TagResponseDTO;
import com.arobs.internship.librarymanagement.model.Tag;
import com.arobs.internship.librarymanagement.repository.TagRepository;
import com.arobs.internship.librarymanagement.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("service")
public class TagServiceImpl implements TagService {


    private final TagRepository tagRepository;

    private final Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public TagResponseDTO addTag(TagRegistrationDTO request) {
        tagRepository.createTag(TagBuilder.generateEntityFromDTORegistration(request));

        return TagBuilder.generateDTOResponseFromEntity(tagRepository.findByTagName(request.getTagName()));
    }

    @Override
    public TagUpdateDTO updateTag(String tagName, String newTag) {
        tagRepository.updateTag(tagName, newTag);
        return TagBuilder.generateDTOUpdateFromEntity(this.tagRepository.findByTagName(newTag));
    }

    @Override
    public boolean deleteTag(String tagName) {
        return this.tagRepository.deleteTag(tagName);
    }

    @Override
    public TagResponseDTO retrieveByTagName(String tagName) {
        return TagBuilder.generateDTOResponseFromEntity(tagRepository.findByTagName(tagName));
    }

    @Override
    public List<TagResponseDTO> retrieveAll() {
        List<TagResponseDTO> tagsResponse = new ArrayList<TagResponseDTO>();
        List<Tag> tags = this.tagRepository.findAll();

        for (Tag tagAux : tags) {
            tagsResponse.add(TagBuilder.generateDTOResponseFromEntity(tagAux));
        }
        return tagsResponse;
    }
}
