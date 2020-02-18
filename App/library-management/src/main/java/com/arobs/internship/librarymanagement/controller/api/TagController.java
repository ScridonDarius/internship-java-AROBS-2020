package com.arobs.internship.librarymanagement.controller.api;

import com.arobs.internship.librarymanagement.controller.api.request.TagRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.TagResponseDTO;
import com.arobs.internship.librarymanagement.service.impl.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/tag",
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class TagController {

    @Autowired
    protected TagServiceImpl tagService;

    @RequestMapping(value = "/createTag", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TagResponseDTO> createTag(
            @RequestBody TagRegistrationDTO request) {

        TagResponseDTO tagResponse = this.tagService.addTag(request);

        return tagResponse != null
                ? new ResponseEntity<>(tagResponse, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/tagName/{tagName}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TagResponseDTO> retrieveByUserName(
            @PathVariable("tagName") String tagName) {

        final TagResponseDTO user =
                this.tagService.retrieveByTagName(tagName);

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(new TagResponseDTO(), HttpStatus.NOT_FOUND);
    }
}
