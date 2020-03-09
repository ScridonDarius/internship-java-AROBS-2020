package com.arobs.internship.librarymanagement.controller.api;

import com.arobs.internship.librarymanagement.controller.api.request.CopyRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.CopyUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.CopyResponseDTO;
import com.arobs.internship.librarymanagement.model.enums.CopyStatus;
import com.arobs.internship.librarymanagement.service.impl.CopyServiceImpl;
import com.arobs.internship.librarymanagement.service.mapperConverter.CopyMapperConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/copy", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CopyController {

    private final CopyServiceImpl copyService;

    public CopyController(CopyServiceImpl copyService) {
        this.copyService = copyService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CopyResponseDTO> createCopyByAuthorAndTitle(@RequestParam String title, @RequestParam String author, @RequestBody CopyRegistrationDTO request) {
        CopyResponseDTO copyResponseDTO = CopyMapperConverter.generateDTOResponseFromEntity(getCopyService().saveCopyByAuthorAndTitle(request, title, author));

        return new ResponseEntity<>(copyResponseDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/createCopyByBookId/{bookId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CopyResponseDTO> createCopyByBookId(@PathVariable("bookId") int bookId, @RequestBody CopyRegistrationDTO request) {
        CopyResponseDTO copyResponseDTO = CopyMapperConverter.generateDTOResponseFromEntity(getCopyService().saveCopyByBookId(request, bookId));

        return new ResponseEntity<>(copyResponseDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/retrieveCopyByAuthorAndTitle", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<CopyResponseDTO>> retrieveCopyByAuthorAndTitle(
            @RequestParam String author,
            @RequestParam String title
    ) {
        Set<CopyResponseDTO> copyResponseDTO = getCopyService().findCopyByAuthorAndTitle(author, title).stream().
                map(copy -> new CopyResponseDTO(copy.getId(), copy.getIsbn(), copy.getCopyCondition(), copy.getCopyStatus(), CopyMapperConverter.generateBookCopyFromCopy(copy.getBook()))).collect(Collectors.toSet());


        return copyResponseDTO != null
                ? new ResponseEntity<>(copyResponseDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/retrieveCopyByBookId/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<CopyResponseDTO>> retrieveCopyByBookId(@PathVariable("id") int id) {
        Set<CopyResponseDTO> copyResponseDTO = getCopyService().findCopyByBookId(id).stream().
                map(copy -> new CopyResponseDTO(copy.getId(), copy.getIsbn(), copy.getCopyCondition(), copy.getCopyStatus(), CopyMapperConverter.generateBookCopyFromCopy(copy.getBook()))).collect(Collectors.toSet());


        return copyResponseDTO != null
                ? new ResponseEntity<>(copyResponseDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/retrieveCopies", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<CopyResponseDTO>> retrieveAll() {
        Set<CopyResponseDTO> copys = getCopyService().findAll().stream().map(copy -> new CopyResponseDTO(copy.getId(), copy.getIsbn(), copy.getCopyCondition(), copy.getCopyStatus(), CopyMapperConverter.generateBookCopyFromCopy(copy.getBook()))).collect(Collectors.toSet());

        return copys != null
                ? new ResponseEntity<>(copys, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/retrieveCopiesByStatusAndBook", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<CopyResponseDTO>> retrieveByStatusAndBook(@RequestParam int bookId, @RequestParam CopyStatus copyStatus) {
        Set<CopyResponseDTO> copies = getCopyService().retrieveCopysByStatusAndBookId(bookId, copyStatus).stream().map(copy -> new CopyResponseDTO(copy.getId(), copy.getIsbn(), copy.getCopyCondition(), copy.getCopyStatus(), CopyMapperConverter.generateBookCopyFromCopy(copy.getBook()))).collect(Collectors.toSet());

        return copies != null
                ? new ResponseEntity<>(copies, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/findCopy/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CopyResponseDTO> retrieveById(
            @PathVariable("id") int id) {
        CopyResponseDTO copyResponseDTO;

        try {
            copyResponseDTO = CopyMapperConverter.generateDTOResponseFromEntity(getCopyService().findById(id));
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This copy doesn't exist!");
        }
        return new ResponseEntity<>(copyResponseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/findCopy", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CopyResponseDTO> retrieveByIsbn(
            @RequestParam String isbn) {
        CopyResponseDTO copyResponseDTO;

        try {
            copyResponseDTO = CopyMapperConverter.generateDTOResponseFromEntity(getCopyService().findByISBN(isbn));
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This copy doesn't exist!");
        }
        return new ResponseEntity<>(copyResponseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateCopy", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CopyUpdateDTO> updateBook(
            @RequestParam int copyId,
            @RequestBody @Valid CopyUpdateDTO request) {
        CopyUpdateDTO copyUpdateDTO;

        try {
            copyUpdateDTO = CopyMapperConverter.generateUpdateDTOeFromEntity(getCopyService().update(request, copyId));

        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This book doesn't exist!");
        }
        return new ResponseEntity<>(copyUpdateDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteCopy/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> deleteCopy(
            @PathVariable("id") int id) {
        boolean response = getCopyService().delete(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public CopyServiceImpl getCopyService() {
        return copyService;
    }
}
