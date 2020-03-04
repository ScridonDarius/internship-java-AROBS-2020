package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.BookRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.BookUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.TagBookResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.TagResponseDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.model.Book;
import com.arobs.internship.librarymanagement.model.Tag;
import com.arobs.internship.librarymanagement.repository.BookRepository;
import com.arobs.internship.librarymanagement.repository.factory.RepositoryFactory;
import com.arobs.internship.librarymanagement.service.BookService;
import com.arobs.internship.librarymanagement.service.converter.ListToSetConverter;
import com.arobs.internship.librarymanagement.service.mapperConverter.BookMapperConverter;
import com.arobs.internship.librarymanagement.service.mapperConverter.TagMapperConverter;
import com.arobs.internship.librarymanagement.validation.ValidationService;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    private final RepositoryFactory repositoryFactory;

    private final TagServiceImpl tagService;

    public BookServiceImpl(RepositoryFactory repositoryFactory, TagServiceImpl tagService) {
        this.repositoryFactory = repositoryFactory;
        this.tagService = tagService;
    }

    @PostConstruct
    public void init() {
        RepositoryFactory factory = repositoryFactory.getInstance();
        bookRepository = factory.getBookRepository();
    }

    // TODO : change list with querry to DataBase (If we have a long list with books, affect our performance)

    @Override
    @Transactional
    public Book addBook(BookRegistrationDTO bookRegistrationDTO) throws FoundException {
        Book book = BookMapperConverter.generateEntityFromDTORegistration(bookRegistrationDTO);
        if (retrieveBookByAuthorAndTitle(bookRegistrationDTO.getAuthor(), bookRegistrationDTO.getTitle()) == null) {
            if (!CollectionUtils.isEmpty(bookRegistrationDTO.getTags())) {
                book.setTags(addTags(bookRegistrationDTO.getTags()));
            }
            book = getBookRepository().save(book);
        } else {
            throw new FoundException();
        }
        return book;
    }

    private Set<Tag> addTags(Set<TagBookResponseDTO> tags) {
        final Set<Tag> results = new HashSet<>();

        final List<Tag> allTags = tagService.getAll();
        final Set<String> tagNames = allTags.stream().map(Tag::getTagName).collect(Collectors.toSet());

        for (TagBookResponseDTO tag : tags) {
            if (!tagNames.contains(tag.getTagName())) {
                results.add(tagService.addTag(TagMapperConverter.generateRegistrationFromTagBookDTO(tag)));
            } else {
                results.add(allTags.stream().filter(tagg -> tag.getTagName().equals(tag.getTagName())).findFirst().get());
            }
        }
        return results;
    }

    @Override
    @Transactional
    public Book retrieveBookByAuthorAndTitle(String author, String title) {
        return ValidationService.safeGetUniqueResult(getBookRepository().findBook(author, title));
    }

    @Override
    @Transactional
    public Book retrieveBookById(int id) {
        return getBookRepository().findBookById(id);
    }

    @Override
    @Transactional
    public boolean deleteBook(int id) {
        final Book book = getBookRepository().findBookById(id);
        if (!Objects.isNull(book)) {
            getBookRepository().delete(book);
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public Book updateBook(BookUpdateDTO bookUpdateDTO, int bookId) {
        final Book book = retrieveBookById(bookId);
        Set<Tag> bookTags = book.getTags();
        final Set<TagBookResponseDTO> booksTag = bookTags.stream().map(tag -> new TagBookResponseDTO(tag.getTagName())).collect(Collectors.toSet());

        final Set<TagBookResponseDTO> requestTags = bookUpdateDTO.getTags();

        final Set<TagBookResponseDTO> results = new HashSet<>();

        results.addAll(requestTags);
        results.addAll(booksTag);

        if (bookUpdateDTO.getDescription().isEmpty()) {
            bookUpdateDTO.setDescription(book.getDescription());
        }

        book.setTags(addTags(results));
        book.setDescription(bookUpdateDTO.getDescription());
        getBookRepository().updateBook(book);
        return book;
    }


    @Override
    @Transactional
    public Set<Book> getAll() {
        return ListToSetConverter.convertListToSet(getBookRepository().getAll());
    }

    protected BookRepository getBookRepository() {
        return bookRepository;
    }

}
