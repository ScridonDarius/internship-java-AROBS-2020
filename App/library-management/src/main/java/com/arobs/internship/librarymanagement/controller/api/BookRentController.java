package com.arobs.internship.librarymanagement.controller.api;

import com.arobs.internship.librarymanagement.controller.api.request.BookRentRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.BookRentUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookRentResponseDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.model.enums.BookRentStatus;
import com.arobs.internship.librarymanagement.service.impl.BookRentServiceImpl;
import com.arobs.internship.librarymanagement.mapperConverter.BookMapperConverter;
import com.arobs.internship.librarymanagement.mapperConverter.BookRentMapperConverter;
import com.arobs.internship.librarymanagement.mapperConverter.CopyMapperConverter;
import com.arobs.internship.librarymanagement.mapperConverter.EmployeeMapperConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.ValidationException;
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
    public ResponseEntity<BookRentResponseDTO> create(@RequestBody BookRentRegistrationDTO request) {
        BookRentResponseDTO bookResponseDTO = new BookRentResponseDTO();
        try {
            bookResponseDTO = BookRentMapperConverter.generateResponseFromEntity(getBookRentService().save(request));
        } catch (FoundException e) {
            throw new ResponseStatusException(HttpStatus.FOUND, "Book already exist in DataBase", e);
        } catch (ValidationException e) {
            if(e.getMessage().equals("Please make a request, someone is waiting for same book")) {
                throw new ResponseStatusException(HttpStatus.FOUND, "Processing fail.Someone is waiting for same book please male a rent request");
            }
            if(e.getMessage().equals("No copies avaliable")){
                throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"Process fail.No copy Available for rent, please make a request");
            }
            if(e.getMessage().equals("You have another rent for this book.")){
                throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"Process fail.You are in DB with rent for this book");
            }
            if(e.getMessage().equals("you are suspended")){
                throw  new ResponseStatusException(HttpStatus.FORBIDDEN,"Process fail.You dont have permission, because you are suspended");
            }
        }

        return new ResponseEntity<>(bookResponseDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/retrieveById/{rentId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookRentResponseDTO> retrieveById(@PathVariable("rentId") Integer rentId) {
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

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        boolean result;
        try {
            result = getBookRentService().delete(id);

        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This bookRent doesn't exist!");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/retriveByStatus", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<BookRentResponseDTO>> retrieveByStatus(@RequestParam BookRentStatus bookRentStatus) {
        Set<BookRentResponseDTO> books = getBookRentService()
                .retrieveByStatus(bookRentStatus)
                .stream()
                .map(book -> new BookRentResponseDTO(book.getId(), book.getRentalDate(), book.getReturnDate(),
                        book.getBookRentStatus(), book.getNote(), BookMapperConverter.generateDTOFromEntity(book.getBook()),
                        EmployeeMapperConverter.generateBookRequestEmployeeFromEntity(book.getEmployee()),
                        CopyMapperConverter.generateDTOFromEntity(book.getCopy()))).collect(Collectors.toSet());

        return books != null
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookRentUpdateDTO> update(@RequestParam int bookId, @RequestBody @Valid BookRentUpdateDTO request) {
        BookRentUpdateDTO bookRequestUpdateDTO;

        try {
            bookRequestUpdateDTO = BookRentMapperConverter.generateUpdateDTOFromEntity(getBookRentService().update(request,bookId));

        } catch (FoundException e) {
            throw new ResponseStatusException(HttpStatus.FOUND, "Processing fail. this bookRent not exist!");
        }
        return new ResponseEntity<>(bookRequestUpdateDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/returnBook", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookRentResponseDTO> returnBook(@RequestParam Integer rentId)throws FoundException {
        BookRentResponseDTO bookRentResponseDTO;

            bookRentResponseDTO = BookRentMapperConverter.generateResponseFromEntity(getBookRentService().renturnBook(rentId));

        return new ResponseEntity<>(bookRentResponseDTO, HttpStatus.OK);
    }


    public BookRentServiceImpl getBookRentService() {
        return bookRentService;
    }
}
