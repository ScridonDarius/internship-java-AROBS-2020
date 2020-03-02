package com.arobs.internship.librarymanagement.controller.api;

import com.arobs.internship.librarymanagement.controller.api.request.BookRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookResponseDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.model.Book;
import com.arobs.internship.librarymanagement.service.impl.BookServiceImpl;
import com.arobs.internship.librarymanagement.service.mapperConverter.BookMapperConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/book", produces = {MediaType.APPLICATION_JSON_VALUE})
public class BookController {

    private final BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRegistrationDTO request) {
        BookResponseDTO bookResponseDTO;
        try {
            bookResponseDTO = BookMapperConverter.generateDTOResponseFromEntity(getBookService().addBook(request));
        } catch (FoundException e) {
            throw new ResponseStatusException(HttpStatus.FOUND, "Book already exist in DataBase", e);
        }

        return new ResponseEntity<>(bookResponseDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/findBook", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Book> retrieveByAuthorAndTitle(
            @RequestParam String author,
            @RequestParam String title) {
        Book bookResponseDTO;

        try {
            bookResponseDTO = getBookService().retrieveBookByAuthorAndTitle(author, title);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This book doesn't exist!");
        }
        return new ResponseEntity<>(bookResponseDTO, HttpStatus.OK);

    }


    @RequestMapping(value = "/deleteBoook/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> deleteBook(
            @PathVariable("id") int id) {
        getBookService().deleteBook(id);

        return new ResponseEntity<>(getBookService().deleteBook(id), HttpStatus.OK);
    }


    protected BookServiceImpl getBookService() {
        return bookService;
    }
}
