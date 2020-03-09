package com.arobs.internship.librarymanagement.controller.api;

import com.arobs.internship.librarymanagement.controller.api.request.BookRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.BookRentRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookCopyDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookRentResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookResponseDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.service.impl.BookRentServiceImpl;
import com.arobs.internship.librarymanagement.service.mapperConverter.BookMapperConverter;
import com.arobs.internship.librarymanagement.service.mapperConverter.BookRentMapperConverter;
import com.arobs.internship.librarymanagement.service.mapperConverter.CopyMapperConverter;
import com.arobs.internship.librarymanagement.service.mapperConverter.EmployeeMapperConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/bookRent", produces = {MediaType.APPLICATION_JSON_VALUE})
public class BookRentController {

    private final BookRentServiceImpl bookRentService;

    public BookRentController(BookRentServiceImpl bookRentService) {
        this.bookRentService = bookRentService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookRentResponseDTO> createBookRent(@RequestBody BookRentRegistrationDTO request) {
        BookRentResponseDTO bookResponseDTO;
        try {
            bookResponseDTO = BookRentMapperConverter.generateResponseFromEntity(getBookRentService().save(request));
        } catch (FoundException e) {
            throw new ResponseStatusException(HttpStatus.FOUND, "Book already exist in DataBase", e);
        }

        return new ResponseEntity<>(bookResponseDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/findBookRent/{rentId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookRentResponseDTO> retrieveById(
            @PathVariable("rentId") Integer rentId) {
        BookRentResponseDTO bookRentResponseDTO;
        try {
            bookRentResponseDTO = BookRentMapperConverter.generateResponseFromEntity(getBookRentService().retrieveById(rentId));
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This rent doesn't exist!");
        }
        return new ResponseEntity<>(bookRentResponseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/retrieveAll", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<BookRentResponseDTO>> retrieveAll() {
        Set<BookRentResponseDTO> books = getBookRentService().retrieveAll().stream()
                .map(book -> new BookRentResponseDTO(book.getId(), book.getRentalDate(), book.getReturnDate(),
                        book.getBookRentStatus(), book.getNote(), BookMapperConverter.generateDTOFromEntity(book.getBook()),
                        EmployeeMapperConverter.generateBookRequestEmployeeFromEntity(book.getEmployee()),
                        CopyMapperConverter.generateDTOFromEntity(book.getCopy()))).collect(Collectors.toSet());

        return books != null
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public BookRentServiceImpl getBookRentService() {
        return bookRentService;
    }
}
