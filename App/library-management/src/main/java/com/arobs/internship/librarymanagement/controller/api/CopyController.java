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
        CopyResponseDTO copyResponseDTO = CopyMapperConverter.generateDTOResponseFromEntity(getCopyService().saveByAuthorAndTitle(request, title, author));

        return new ResponseEntity<>(copyResponseDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/create/{bookId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CopyResponseDTO> createCopyByBookId(@PathVariable("bookId") int bookId, @RequestBody CopyRegistrationDTO request) {
        CopyResponseDTO copyResponseDTO = CopyMapperConverter.generateDTOResponseFromEntity(getCopyService().saveByBookId(request, bookId));

        return new ResponseEntity<>(copyResponseDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/retrieveByAuthorAndTitle", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<CopyResponseDTO>> retrieveByAuthorAndTitle(@RequestParam String author, @RequestParam String title) {
        Set<CopyResponseDTO> copyResponseDTO = getCopyService().retrieveByAuthorAndTitle(author, title).stream().
                map(copy -> new CopyResponseDTO(copy.getId(), copy.getIsbn(), copy.getCopyCondition(), copy.getCopyStatus(), CopyMapperConverter.generateBookCopyFromCopy(copy.getBook()))).collect(Collectors.toSet());

        return copyResponseDTO != null
                ? new ResponseEntity<>(copyResponseDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/retrieveByBookId/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<CopyResponseDTO>> retrieveByBookId(@PathVariable("id") int id) {
        Set<CopyResponseDTO> copyResponseDTO = getCopyService().retrieveByBookId(id).stream().
                map(copy -> new CopyResponseDTO(copy.getId(), copy.getIsbn(), copy.getCopyCondition(), copy.getCopyStatus(), CopyMapperConverter.generateBookCopyFromCopy(copy.getBook()))).collect(Collectors.toSet());

        return copyResponseDTO != null
                ? new ResponseEntity<>(copyResponseDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/retrieveAll", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<CopyResponseDTO>> retrieveAll() {
        Set<CopyResponseDTO> copys = getCopyService().retrieveAll().stream().map(copy -> new CopyResponseDTO(copy.getId(), copy.getIsbn(), copy.getCopyCondition(), copy.getCopyStatus(), CopyMapperConverter.generateBookCopyFromCopy(copy.getBook()))).collect(Collectors.toSet());

        return copys != null
                ? new ResponseEntity<>(copys, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/retrieveByStatusAndBookId", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<CopyResponseDTO>> retrieveByStatusAndBookId(@RequestParam int bookId, @RequestParam CopyStatus copyStatus) {
        Set<CopyResponseDTO> copies = getCopyService().retrieveByStatusAndBookId(bookId, copyStatus).stream().map(copy -> new CopyResponseDTO(copy.getId(), copy.getIsbn(), copy.getCopyCondition(), copy.getCopyStatus(), CopyMapperConverter.generateBookCopyFromCopy(copy.getBook()))).collect(Collectors.toSet());

        return copies != null
                ? new ResponseEntity<>(copies, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/retrieveById/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CopyResponseDTO> retrieveById(@PathVariable("id") int id) {
        CopyResponseDTO copyResponseDTO;

        try {
            copyResponseDTO = CopyMapperConverter.generateDTOResponseFromEntity(getCopyService().retrieveById(id));
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This copy doesn't exist!");
        }
        return new ResponseEntity<>(copyResponseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/retrieveByISBN", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CopyResponseDTO> retrieveByIsbn(@RequestParam String isbn) {
        CopyResponseDTO copyResponseDTO;

        try {
            copyResponseDTO = CopyMapperConverter.generateDTOResponseFromEntity(getCopyService().retrieveByISBN(isbn));
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This copy doesn't exist!");
        }
        return new ResponseEntity<>(copyResponseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CopyUpdateDTO> update(@RequestParam Integer copyId, @RequestBody @Valid CopyUpdateDTO request) {
        CopyUpdateDTO copyUpdateDTO;

        try {
            copyUpdateDTO = CopyMapperConverter.generateUpdateDTOeFromEntity(getCopyService().update(request, copyId));
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This book doesn't exist!");
        }
        return new ResponseEntity<>(copyUpdateDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> delete(@PathVariable("id") int id) {
        boolean response = getCopyService().delete(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public CopyServiceImpl getCopyService() {
        return copyService;
    }
}
