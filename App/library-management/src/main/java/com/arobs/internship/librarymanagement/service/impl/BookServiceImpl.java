package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.BookRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.TagResponseDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.model.Book;
import com.arobs.internship.librarymanagement.model.Tag;
import com.arobs.internship.librarymanagement.repository.BookRepository;
import com.arobs.internship.librarymanagement.repository.factory.RepositoryFactory;
import com.arobs.internship.librarymanagement.repository.hibernate.BookRepositoryHibernateImpl;
import com.arobs.internship.librarymanagement.repository.jdbc.mapper.BookMapper;
import com.arobs.internship.librarymanagement.service.BookService;
import com.arobs.internship.librarymanagement.service.converter.ListToSetConverter;
import com.arobs.internship.librarymanagement.service.mapperConverter.BookMapperConvertor;
import com.arobs.internship.librarymanagement.service.mapperConverter.TagMapperConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.*;

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

    @Override
    @Transactional
    public BookResponseDTO addBook(BookRegistrationDTO bookRegistrationDTO) throws FoundException {
        Book book = BookMapperConvertor.generateEntityFromDTORegistration(bookRegistrationDTO);
        if (!CollectionUtils.isEmpty(bookRegistrationDTO.getTags())) {
            book.setTags(addTags(bookRegistrationDTO.getTags()));
        }

        // TODO : change list with querry to DataBase (If we have a long list with books, affect our performance)

        if (retrieveBookByAuthorAndTitle(bookRegistrationDTO.getAuthor(), bookRegistrationDTO.getTitle()) == null) {
            book = getBookRepository().save(book);
        } else {
            throw new FoundException();
        }
        return BookMapperConvertor.generateDTOResponseFromEntity(book);
    }

    private Set<Tag> addTags(Set<TagResponseDTO> tags) {
        final Set<Tag> results = new HashSet<>();
        final Set<TagResponseDTO> tagsResponseDTO = tagService.getAll();

        for (TagResponseDTO tag : tags) {
            if (!tagsResponseDTO.contains(tag)) {
                results.add(tagService.getTagRepository().createTag(TagMapperConverter.generateEntityFromDTOResponse(tag)));
            } else {
                results.add(tagService.getTagRepository().findByTagName(tag.getTagName()));
            }
        }
        return results;
    }

    @Override
    @Transactional
    public BookResponseDTO retrieveBookByAuthorAndTitle(String author, String title) {
        List<Book> book = getBookRepository().findBook(author, title);

        if (book.isEmpty() || book == null) {
            return null;
        } else {
            return BookMapperConvertor.generateDTOResponseFromEntity(book.get(0));
        }
    }

    @Override
    @Transactional
    public BookResponseDTO retrieveBookById(int id) {
        return BookMapperConvertor.generateDTOResponseFromEntity(getBookRepository().findBookById(id));
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
    public Set<BookResponseDTO> getAll() {
        List<BookResponseDTO> bookResponseDTO = new ArrayList<>();
        List<Book> books = getBookRepository().getAll();

        for (Book bookAux : books) {
            bookResponseDTO.add(BookMapperConvertor.generateDTOResponseFromEntity(bookAux));
        }
        return ListToSetConverter.convertListToSet(bookResponseDTO);
    }

    protected BookRepository getBookRepository() {
        return bookRepository;
    }

}
