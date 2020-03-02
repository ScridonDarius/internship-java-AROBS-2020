package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.BookRegistrationDTO;
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
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.HashSet;
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
        if (!CollectionUtils.isEmpty(bookRegistrationDTO.getTags())) {
            book.setTags(addTags(bookRegistrationDTO.getTags()));
        }

        if (retrieveBookByAuthorAndTitle(bookRegistrationDTO.getAuthor(), bookRegistrationDTO.getTitle()) == null) {
            book = getBookRepository().save(book);
        } else {
            throw new FoundException();
        }
        return book;
    }

    private Set<Tag> adddTags(Set<TagResponseDTO> tags) {
        final Set<Tag> results = new HashSet<>();
        final Set<Tag> allTags = tagService.getAll();

        for (TagResponseDTO tag : tags) {
            if (!allTags.contains(tag)) {
                results.add(tagService.addTag(TagMapperConverter.generateRegistrationFromResponse(tag)));
            } else {
                results.add(tagService.retrieveByTagName(tag.getTagName()));
            }
        }
        return results;
    }

    private Set<Tag> addTags(Set<TagResponseDTO> tags) {
        final Set<Tag> results = new HashSet<>();
        final Set<TagResponseDTO> tagsResponseDTO  = tagService.getAll().stream().map(tag -> new TagResponseDTO(tag.getTagName())).collect(Collectors.toSet());


        for (TagResponseDTO tag : tags) {
            if (!tagsResponseDTO.contains(tag)) {
                results.add(tagService.getTagRepository().createTag(TagMapperConverter.generateEntityFromDTOResponse(tag)));
            } else {
                results.add(tagService.retrieveByTagName(tag.getTagName()));
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
    public Set<Book> getAll() {
        return ListToSetConverter.convertListToSet(getBookRepository().getAll());
    }

    protected BookRepository getBookRepository() {
        return bookRepository;
    }

}
