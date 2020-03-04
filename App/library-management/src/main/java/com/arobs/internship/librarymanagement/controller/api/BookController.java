package com.arobs.internship.librarymanagement.controller.api;

import com.arobs.internship.librarymanagement.controller.api.request.BookRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.BookUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookResponseDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.model.Book;
import com.arobs.internship.librarymanagement.repository.jdbc.mapper.BookMapper;
import com.arobs.internship.librarymanagement.service.impl.BookServiceImpl;
import com.arobs.internship.librarymanagement.service.mapperConverter.BookMapperConverter;
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
    public ResponseEntity<BookResponseDTO> retrieveByAuthorAndTitle(
            @RequestParam String author,
            @RequestParam String title) {
        BookResponseDTO bookResponseDTO;

        try {
            bookResponseDTO = BookMapperConverter.generateDTOResponseFromEntity(this.getBookService().retrieveBookByAuthorAndTitle(author,title));
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This book doesn't exist!");
        }
        return new ResponseEntity<>(bookResponseDTO, HttpStatus.OK);

    }

    @RequestMapping(value = "/findBook/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookResponseDTO> retrieveById(
            @PathVariable("id") int id )         {
        BookResponseDTO bookResponseDTO;

        try {
            bookResponseDTO = BookMapperConverter.generateDTOResponseFromEntity(this.getBookService().retrieveBookById(id));
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This book doesn't exist!");
        }
        return new ResponseEntity<>(bookResponseDTO, HttpStatus.OK);

    }

    @RequestMapping(value = "/updateBook", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BookUpdateDTO> updateBook(
            @RequestParam int bookId,
            @RequestBody @Valid BookUpdateDTO request) {
        BookUpdateDTO bookUpdateDTO;

        try {
            bookUpdateDTO = BookMapperConverter.generateUpdateDTOFromEntity(getBookService().updateBook(request, bookId));

        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This book doesn't exist!");
        }
        return new ResponseEntity<>(bookUpdateDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteBoook/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> deleteBook(
            @PathVariable("id") int id) {
        getBookService().deleteBook(id);

        return new ResponseEntity<>(getBookService().deleteBook(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/retrieveBooks", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<BookResponseDTO>> retrieveAll() {
        Set<BookResponseDTO> books = getBookService().getAll().stream().map(book -> new BookResponseDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getTags())).collect(Collectors.toSet());

        return books != null
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    protected BookServiceImpl getBookService() {
        return bookService;
    }
}
