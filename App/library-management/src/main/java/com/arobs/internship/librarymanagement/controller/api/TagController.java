package com.arobs.internship.librarymanagement.controller.api;

import com.arobs.internship.librarymanagement.controller.api.request.TagRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.TagUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.TagResponseDTO;
import com.arobs.internship.librarymanagement.mapperConverter.TagMapperConverter;
import com.arobs.internship.librarymanagement.service.TagService;
import com.arobs.internship.librarymanagement.service.impl.TagServiceImpl;
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

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TagResponseDTO> create(@RequestBody TagRegistrationDTO request) {
        TagResponseDTO tagResponse = TagMapperConverter.generateDTOResponseFromEntity(getTagService().save(request));

        return tagResponse != null
                ? new ResponseEntity<>(tagResponse, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/retrieveByTagName", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TagResponseDTO> retrieveByTagName(@RequestParam String tagName) {
        TagResponseDTO tag;

        try {
            tag = TagMapperConverter.generateDTOResponseFromEntity(getTagService().retrieveByTagName(tagName));
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This tag doesn't exist!");
        }
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TagUpdateDTO> update(@RequestParam String tagName,
                                               @RequestParam String newTag) {
        TagUpdateDTO tag;

        try {
            tag = TagMapperConverter.generateDTOUpdateFromEntity(getTagService().update(tagName, newTag));
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This tag doesn't exist!");
        }
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> delete(@RequestParam String tagName) {
        boolean result;

        try {
            result = getTagService().delete(tagName);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This tag doesn't exist!");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/retrieveAll", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<TagResponseDTO>> retrieveAll() {
        Set<TagResponseDTO> tags = getTagService().getAll()
                .stream()
                .map(tag -> new TagResponseDTO(tag.getId(), tag.getTagName()))
                .collect(Collectors.toSet());

        return tags != null
                ? new ResponseEntity<>(tags, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public TagService getTagService() {
        return tagService;
    }
}
