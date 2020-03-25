package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.BookRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.BookUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.TagBookResponseDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.mapperConverter.BookMapperConverter;
import com.arobs.internship.librarymanagement.mapperConverter.TagMapperConverter;
import com.arobs.internship.librarymanagement.model.Book;
import com.arobs.internship.librarymanagement.model.Tag;
import com.arobs.internship.librarymanagement.model.enums.BookStatus;
import com.arobs.internship.librarymanagement.repository.BookRepository;
import com.arobs.internship.librarymanagement.service.BookService;
import com.arobs.internship.librarymanagement.service.TagService;
import com.arobs.internship.librarymanagement.service.converter.ListToSetConverter;
import com.arobs.internship.librarymanagement.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TagService tagService;

    @Override
    @Transactional
    public Book save(BookRegistrationDTO bookRegistrationDTO) throws FoundException {
        Book book = BookMapperConverter.generateEntityFromDTORegistration(bookRegistrationDTO);

        if (retrieveByAuthorAndTitle(bookRegistrationDTO.getAuthor(), bookRegistrationDTO.getTitle()) == null) {
            if (!CollectionUtils.isEmpty(bookRegistrationDTO.getTags())) {
                book.setTags(addTags(bookRegistrationDTO.getTags()));
            }
            book.setBookStatus(BookStatus.ACTIVE);
            book = getBookRepository().save(book);
        } else {
            throw new FoundException();
        }
        return book;
    }

    @Override
    @Transactional
    public Book retrieveByAuthorAndTitle(String author, String title) {
        return ValidationService.safeGetUniqueResult(getBookRepository().findByAuthorAndTitle(author, title));
    }

    @Override
    @Transactional
    public Book retrieveById(int id) {
        return ValidationService.safeGetUniqueResult(getBookRepository().findById(id));
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        final Book book = retrieveById(id);
        book.setBookStatus(BookStatus.DELETED);

        if (book == null) {
            return false;
        }
        return true;
        //TODO : check if exist copy, and is AVAILABLE,RENT,PENDING, you can't make delete
    }

    @Override
    @Transactional
    public Book update(BookUpdateDTO bookUpdateDTO, int bookId) {
        final Book book = retrieveById(bookId);
        final Set<TagBookResponseDTO> requestTags = bookUpdateDTO.getTags();
        final Set<TagBookResponseDTO> booksTag = book.getTags().stream().map(tag -> new TagBookResponseDTO(tag.getTagName())).collect(Collectors.toSet());

        requestTags.addAll(booksTag);

        if (bookUpdateDTO.getDescription().isEmpty()) {
            bookUpdateDTO.setDescription(book.getDescription());
        }

        if (bookUpdateDTO.getTags().isEmpty()) {
            bookUpdateDTO.setTags(booksTag);
        }
        book.setTags(addTags(requestTags));
        book.setDescription(bookUpdateDTO.getDescription());

        return book;
    }

    @Override
    @Transactional
    public Set<Book> getAll() {
        return ListToSetConverter.convertListToSet(getBookRepository().getAll());
    }

    private Set<Tag> addTags(Set<TagBookResponseDTO> tags) {
        final Set<Tag> results = new HashSet<>();

        final List<Tag> allTags = tagService.getAll();
        final Set<String> tagNames = allTags.stream().map(Tag::getTagName).collect(Collectors.toSet());

        for (TagBookResponseDTO tag : tags) {
            if (!tag.getTagName().isEmpty()) {
                if (!tagNames.contains(tag.getTagName())) {
                    results.add(tagService.save(TagMapperConverter.generateRegistrationFromTagBookDTO(tag)));
                } else {
                    results.add(allTags.stream().filter(tagg -> tag.getTagName().equals(tagg.getTagName())).findFirst().get());
                }
            }
        }
        return results;
    }

    protected BookRepository getBookRepository() {
        return bookRepository;
    }
}
