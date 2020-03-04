package com.arobs.internship.librarymanagement.controller.api;

import com.arobs.internship.librarymanagement.controller.api.request.TagRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.TagUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.TagResponseDTO;
import com.arobs.internship.librarymanagement.repository.jdbc.mapper.TagMapper;
import com.arobs.internship.librarymanagement.service.impl.TagServiceImpl;
import com.arobs.internship.librarymanagement.service.mapperConverter.TagMapperConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(
        value = "/tag",
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class TagController {

    private final TagServiceImpl tagService;

    public TagController(TagServiceImpl tagService) {
        this.tagService = tagService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TagResponseDTO> createTag(
            @RequestBody TagRegistrationDTO request) {

        TagResponseDTO tagResponse = TagMapperConverter.generateDTOResponseFromEntity(getTagService().addTag(request));

        return tagResponse != null
                ? new ResponseEntity<>(tagResponse, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/retrieveTagByName", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TagResponseDTO> retrieveByTagName(
            @RequestParam String tagName) {
        TagResponseDTO tag;

        try {
            tag = TagMapperConverter.generateDTOResponseFromEntity(getTagService().retrieveByTagName(tagName));
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This tag doesn't exist!");
        }
        return new ResponseEntity<>(tag, HttpStatus.OK);

    }

    @RequestMapping(value = "/updateTag", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TagUpdateDTO> updateTag(
            @RequestParam String tagName,
            @RequestParam String newTag) {
        TagUpdateDTO tag;

        try {
            tag = TagMapperConverter.generateDTOUpdateFromEntity(getTagService().updateTag(tagName, newTag));

        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This tag doesn't exist!");
        }
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteTag", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> deleteTag(@RequestParam String tagName) {

        try {
            getTagService().deleteTag(tagName);

        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This tag doesn't exist!");
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @RequestMapping(value = "/retrieveTags", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<TagResponseDTO>> retrieveAll() {
        Set<TagResponseDTO> tags = getTagService().getAll().stream().map(tag -> new TagResponseDTO(tag.getId(), tag.getTagName())).collect(Collectors.toSet());

        return tags != null
                ? new ResponseEntity<>(tags, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public TagServiceImpl getTagService() {
        return tagService;
    }
}
