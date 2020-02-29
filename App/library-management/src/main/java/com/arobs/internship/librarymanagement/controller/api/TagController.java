package com.arobs.internship.librarymanagement.controller.api;

import com.arobs.internship.librarymanagement.controller.api.request.TagRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.TagUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.TagResponseDTO;
import com.arobs.internship.librarymanagement.exception.InvalidEmailException;
import com.arobs.internship.librarymanagement.exception.NullObjectException;
import com.arobs.internship.librarymanagement.service.impl.TagServiceImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

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

        TagResponseDTO tagResponse = this.tagService.addTag(request);

        return tagResponse != null
                ? new ResponseEntity<>(tagResponse, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/tagName", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TagResponseDTO> retrieveByTagName(
            @RequestParam String tagName) {
        TagResponseDTO tag = null;

        try {
            tag = this.tagService.retrieveByTagName(tagName);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. Got a null response");
        }
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateTag", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TagUpdateDTO> updateTag(
            @RequestParam String tagName,
            @RequestParam String newTag) {
        TagUpdateDTO tag = null;

        try {
            tag = this.tagService.updateTag(tagName, newTag);

        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. Got a null response");
        }
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @RequestMapping(value = "deleteTag", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> deleteTag(@RequestParam String tagName) {

        try {
            this.tagService.deleteTag(tagName);

        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. Got a null response");
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @RequestMapping(value = "retrieveAll", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<TagResponseDTO>> retrieveAll() {
        Set<TagResponseDTO> tags = this.tagService.retrieveAll();

        return tags != null
                ? new ResponseEntity<>(tags, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
