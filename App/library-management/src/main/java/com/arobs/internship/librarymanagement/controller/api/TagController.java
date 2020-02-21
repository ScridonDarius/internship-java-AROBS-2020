package com.arobs.internship.librarymanagement.controller.api;

import com.arobs.internship.librarymanagement.controller.api.request.TagRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.TagUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.TagResponseDTO;
import com.arobs.internship.librarymanagement.service.impl.TagServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        final TagResponseDTO tag =
                this.tagService.retrieveByTagName(tagName);

        return tag != null
                ? new ResponseEntity<>(tag, HttpStatus.OK)
                : new ResponseEntity<>(new TagResponseDTO(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/updateTag", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TagUpdateDTO> updateTag(
            @RequestParam String tagName,
            @RequestParam String newTag) {

        TagUpdateDTO tag = this.tagService.updateTag(tagName, newTag);
        return tag != null
                ? new ResponseEntity<>(tag, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "deleteTag", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> deleteTag(@RequestParam String tagName) {

        return new ResponseEntity<>(this.tagService.deleteTag(tagName), HttpStatus.OK);
    }

    @RequestMapping(value = "retrieveAll", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TagResponseDTO>> retrieveAll() {
        List<TagResponseDTO> tags = this.tagService.retrieveAll();

        return tags != null
                ? new ResponseEntity<>(tags, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
