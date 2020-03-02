package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.TagRegistrationDTO;
import com.arobs.internship.librarymanagement.model.Tag;
import com.arobs.internship.librarymanagement.repository.TagRepository;
import com.arobs.internship.librarymanagement.repository.factory.RepositoryFactory;
import com.arobs.internship.librarymanagement.service.TagService;
import com.arobs.internship.librarymanagement.service.converter.ListToSetConverter;
import com.arobs.internship.librarymanagement.service.mapperConverter.TagMapperConverter;
import com.arobs.internship.librarymanagement.validation.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
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
    public Tag addTag(TagRegistrationDTO request) {
        Tag tag = getTagRepository().createTag(TagMapperConverter.generateEntityFromDTORegistration(request));
        return tag;
    }

    @Transactional
    @Override
    public Tag updateTag(String tagName, String newTag) {
        final Tag tag = retrieveByTagName(tagName);
        tag.setTagName(newTag);
        getTagRepository().updateTag(tag);

       return tag;
    }

    @Transactional
    @Override
    public boolean deleteTag(String tagName) {
        final Tag tag = retrieveByTagName(tagName);
        getTagRepository().deleteTag(tag);
        return true;
    }

    @Transactional
    @Override
    public Tag retrieveByTagName(String tagName) {
        List<Tag> tags = getTagRepository().findByTagName(tagName);
        Tag tag = ValidationService.safeGetUniqueResult(tags);
        return tag;
    }

    @Transactional
    @Override
    public Set<Tag> getAll() {
        return ListToSetConverter.convertListToSet(getTagRepository().findAll());
    }

    protected TagRepository getTagRepository() {
        return tagRepository;
    }
}
