package com.arobs.internship.librarymanagement.controller.api;

import com.arobs.internship.librarymanagement.controller.api.request.BookRequestRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.BookRequestUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.request.BookUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookRequestResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.TagResponseDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.model.BookRequest;
import com.arobs.internship.librarymanagement.model.enums.BookRequestStatus;
import com.arobs.internship.librarymanagement.service.impl.BookRequestServiceImpl;
import com.arobs.internship.librarymanagement.service.mapperConverter.BookMapperConverter;
import com.arobs.internship.librarymanagement.service.mapperConverter.BookRequestMapperConverter;
import com.arobs.internship.librarymanagement.service.mapperConverter.EmployeeMapperConverter;
import com.arobs.internship.librarymanagement.service.mapperConverter.TagMapperConverter;
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
@RequestMapping(value = "/bookRequest", produces = {MediaType.APPLICATION_JSON_VALUE})
public class BookRequestController {

    final BookRequestServiceImpl bookRequestService;

    public BookRequestController(BookRequestServiceImpl bookRequestService) {
        this.bookRequestService = bookRequestService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookRequestResponseDTO> createBookRequestRegistration(@RequestBody BookRequestRegistrationDTO request) {
        BookRequestResponseDTO bookRequestResponseDTO;
        try {
            bookRequestResponseDTO = BookRequestMapperConverter.generateDTOResponseFromEntity(getBookRequestService().save(request));
        } catch (FoundException e) {
            throw new ResponseStatusException(HttpStatus.FOUND, "BookRequest already exist in process, or book already exist in library", e);
        }

        return new ResponseEntity<>(bookRequestResponseDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/retrieveById/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookRequestResponseDTO> retrieveById(
            @PathVariable("id") Integer id) {
        BookRequestResponseDTO bookRequestResponseDTO;

        try {
            bookRequestResponseDTO = BookRequestMapperConverter.generateDTOResponseFromEntity(getBookRequestService().retrieveById(id));
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This tag doesn't exist!");
        }
        return new ResponseEntity<>(bookRequestResponseDTO, HttpStatus.OK);
    }


    @RequestMapping(value = "/findBookRequestByAuthorAndTitle", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookRequestResponseDTO> retrieveByAuthorAndTitle(
            @RequestParam String author,
            @RequestParam String title) {
        BookRequestResponseDTO bookResponseDTO;

        try {
            bookResponseDTO = BookRequestMapperConverter.generateDTOResponseFromEntity(getBookRequestService().retrieveByAuthorAndTitle(author, title));
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This bookRequest doesn't exist!");
        }
        return new ResponseEntity<>(bookResponseDTO, HttpStatus.OK);

    }

    @RequestMapping(value = "/deleteBookRequest/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        boolean result;
        try {
            result = getBookRequestService().delete(id);

        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This bookRequest doesn't exist!");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/retriveBooksRequest", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<BookRequestResponseDTO>> retrieveAll() {
        Set<BookRequestResponseDTO> books = getBookRequestService()
                .retrieveAll()
                .stream()
                .map(book -> new BookRequestResponseDTO(book.getId(), book.getTitle(), book.getAuthor(),
                        book.getPublishingCompany(), book.getCopyNumber(), book.getTotalCost(), book.getBookRequestStatus(),
                        EmployeeMapperConverter.generateBookRequestEmployeeFromEntity(book.getEmployee()))).collect(Collectors.toSet());

        return books != null
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/retriveByStatus", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<BookRequestResponseDTO>> retrieveByStatus(@RequestParam BookRequestStatus bookRequestStatus) {
        Set<BookRequestResponseDTO> books = getBookRequestService()
                .retrieveByStatus(bookRequestStatus)
                .stream()
                .map(book -> new BookRequestResponseDTO(book.getId(), book.getTitle(), book.getAuthor(),
                        book.getPublishingCompany(), book.getCopyNumber(), book.getTotalCost(), book.getBookRequestStatus(),
                        EmployeeMapperConverter.generateBookRequestEmployeeFromEntity(book.getEmployee()))).collect(Collectors.toSet());

        return books != null
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/updateBookRequest", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookRequestUpdateDTO> updateBook(
            @RequestParam int bookId,
            @RequestBody @Valid BookRequestUpdateDTO request) {
        BookRequestUpdateDTO bookRequestUpdateDTO;

        try {
            bookRequestUpdateDTO = BookRequestMapperConverter.generateDTOUpdateFromEntity(getBookRequestService().update(request,bookId));

        }catch (FoundException e) {
            throw new ResponseStatusException(HttpStatus.FOUND, "Processing fail. this book not exist!");
        }
        return new ResponseEntity<>(bookRequestUpdateDTO, HttpStatus.OK);
    }


    public BookRequestServiceImpl getBookRequestService() {
        return bookRequestService;
    }
}
