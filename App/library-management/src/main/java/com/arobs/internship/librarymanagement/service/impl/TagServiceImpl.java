package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.TagRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.TagUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.TagResponseDTO;
import com.arobs.internship.librarymanagement.model.Tag;
import com.arobs.internship.librarymanagement.repository.TagRepository;
import com.arobs.internship.librarymanagement.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagRepository tagRepository;

    private final Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);

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
    public TagUpdateDTO updateTag(String tagName, String newTag) {
        tagRepository.updateTag(tagName, newTag);
        Tag tag = new Tag();
        try {
            tag = this.tagRepository.findByTagName(newTag);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new TagUpdateDTO(
                tag.getId(),
                tag.getTagName());
    }

    @Override
    public boolean deleteTag(String tagName) {
        TagResponseDTO tag = retrieveByTagName(tagName);
        if (tag == null){
            logger.warn("No tag found for deletion.tag_name=" + tagName);
            throw new ValidationException("No tag found for deletion");
        }
        return this.tagRepository.deleteTag(tagName);
    }

    @Override
    public TagResponseDTO retrieveByTagName(String tagName) {
        Tag tag = new Tag();
        try {
            tag = this.tagRepository.findByTagName(tagName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return
                new TagResponseDTO(
                        tag.getId(),
                        tag.getTagName());
    }

    @Override
    public List<TagResponseDTO> retrieveAll() {

        List<TagResponseDTO> tagsResponse = new ArrayList<TagResponseDTO>();
        List<Tag> tags = this.tagRepository.findAll();

        for(Tag tagAux : tags){

            TagResponseDTO tagDTO =
                    new TagResponseDTO(
                            tagAux.getId(),
                            tagAux.getTagName());

            tagsResponse.add(tagDTO);
        }
        return tagsResponse;
    }

}
