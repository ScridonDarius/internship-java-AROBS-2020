package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.TagRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.TagUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.TagResponseDTO;
import com.arobs.internship.librarymanagement.model.Tag;
import com.arobs.internship.librarymanagement.repository.TagRepository;
import com.arobs.internship.librarymanagement.repository.factory.RepositoryFactory;
import com.arobs.internship.librarymanagement.service.TagService;
import com.arobs.internship.librarymanagement.service.converter.ListToSetConverter;
import com.arobs.internship.librarymanagement.service.mapperConverter.TagMapperConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    private final Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);

    private final RepositoryFactory repositoryFactory;

    public TagServiceImpl(RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    @PostConstruct
    public void init() {
        RepositoryFactory factory = repositoryFactory.getInstance();
        tagRepository = factory.getTagRepository();
    }

    @Transactional
    @Override
    public TagResponseDTO addTag(TagRegistrationDTO request) {
        tagRepository.createTag(TagMapperConverter.generateEntityFromDTORegistration(request));

        return TagMapperConverter.generateDTOResponseFromEntity(tagRepository.findByTagName(request.getTagName()));
    }

    @Transactional
    @Override
    public TagUpdateDTO updateTag(String tagName, String newTag) {
        final Tag tag = getTagRepository().findByTagName(tagName);
        tag.setTagName(newTag);
        getTagRepository().updateTag(tag);

        return TagMapperConverter.generateDTOUpdateFromEntity(tag);
    }

    @Transactional
    @Override
    public boolean deleteTag(String tagName) {
        final Tag tag = getTagRepository().findByTagName(tagName);
        getTagRepository().deleteTag(tag);
        return true;
    }

    @Transactional
    @Override
    public TagResponseDTO retrieveByTagName(String tagName) {
        if (this.tagRepository.findByTagName(tagName) != null) {
            return TagMapperConverter.generateDTOResponseFromEntity(tagRepository.findByTagName(tagName));
        }
        return null;
    }

    @Transactional
    @Override
    public Set<TagResponseDTO> getAll() {
        List<TagResponseDTO> tagsResponse = new ArrayList<>();
        List<Tag> tags = this.tagRepository.findAll();

        for (Tag tagAux : tags) {
            tagsResponse.add(TagMapperConverter.generateDTOResponseFromEntity(tagAux));
        }
        return ListToSetConverter.convertListToSet(tagsResponse);
    }

    protected TagRepository getTagRepository() {
        return tagRepository;
    }
}
