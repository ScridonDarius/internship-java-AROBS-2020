package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.TagRegistrationDTO;
import com.arobs.internship.librarymanagement.model.Tag;
import com.arobs.internship.librarymanagement.repository.TagRepository;
import com.arobs.internship.librarymanagement.repository.factory.RepositoryFactory;
import com.arobs.internship.librarymanagement.service.TagService;
import com.arobs.internship.librarymanagement.service.mapperConverter.TagMapperConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private  RepositoryFactory repositoryFactory;

    private final Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);

    @PostConstruct
    public void init() {
        RepositoryFactory factory = repositoryFactory.getInstance();
        tagRepository = factory.getTagRepository();
    }

    @Transactional
    @Override
    public Tag addTag(TagRegistrationDTO request) {
        return getTagRepository().createTag(TagMapperConverter.generateEntityFromDTORegistration(request));
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
        if (!Objects.isNull(tag)) {
            getTagRepository().deleteTag(tag);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public Tag retrieveByTagName(String tagName) {
        return getTagRepository().findByTagName(tagName);
    }

    @Override
    @Transactional
    public List<Tag> getAll() {
        return getTagRepository().findAll();
    }

    protected TagRepository getTagRepository() {
        return tagRepository;
    }
}
