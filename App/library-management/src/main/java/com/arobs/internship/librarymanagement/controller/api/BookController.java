package com.arobs.internship.librarymanagement.controller.api;

import com.arobs.internship.librarymanagement.controller.api.request.BookRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookResponseDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.service.impl.BookServiceImpl;
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

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRegistrationDTO request) {
        BookResponseDTO bookResponseDTO;
        try {
            bookResponseDTO = getBookService().addBook(request);
        } catch (FoundException e) {
            throw new ResponseStatusException(HttpStatus.FOUND, "Book already exist in DataBase", e);
        }

        return new ResponseEntity<>(bookResponseDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/findBook", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookResponseDTO> retrieveByAuthorAndTitle(
            @RequestParam String author,
            @RequestParam String title) {
        BookResponseDTO bookResponseDTO = this.bookService.retrieveBookByAuthorAndTitle(author, title);

        return bookResponseDTO != null
                ? new ResponseEntity<>(bookResponseDTO, HttpStatus.OK)
                : new ResponseEntity<>(new BookResponseDTO(), HttpStatus.NO_CONTENT);
    }

    protected BookServiceImpl getBookService() {
        return bookService;
    }
}
