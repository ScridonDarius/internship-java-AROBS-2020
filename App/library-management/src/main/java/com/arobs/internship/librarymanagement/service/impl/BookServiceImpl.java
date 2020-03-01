package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.BookRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.TagResponseDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.model.Book;
import com.arobs.internship.librarymanagement.model.Tag;
import com.arobs.internship.librarymanagement.repository.BookRepository;
import com.arobs.internship.librarymanagement.repository.factory.RepositoryFactory;
import com.arobs.internship.librarymanagement.service.BookService;
import com.arobs.internship.librarymanagement.service.ValidationService;
import com.arobs.internship.librarymanagement.service.converter.ListToSetConverter;
import com.arobs.internship.librarymanagement.service.mapperConverter.BookMapperConvertor;
import com.arobs.internship.librarymanagement.service.mapperConverter.TagMapperConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    private RepositoryFactory repositoryFactory;

    @Autowired
    private TagServiceImpl tagService;

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

        if (Objects.isNull(retrieveBookByAuthorAndTitle(bookRegistrationDTO.getAuthor(), bookRegistrationDTO.getTitle()))) {
            book = bookRepository.save(book);
        } else {
            throw new FoundException("Book already exist in DataBase");
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
        return BookMapperConvertor.generateDTOResponseFromEntity(bookRepository.findBook(author, title));

    }

    @Override
    @Transactional
    public BookResponseDTO retrieveBookById(int id) {
        return BookMapperConvertor.generateDTOResponseFromEntity(bookRepository.findBookById(id));
    }

    @Transactional
    @Override
    public Set<BookResponseDTO> getAll() {
        List<BookResponseDTO> bookResponseDTO = new ArrayList<BookResponseDTO>();
        List<Book> books = this.bookRepository.getAll();

        for (Book bookAux : books) {
            bookResponseDTO.add(BookMapperConvertor.generateDTOResponseFromEntity(bookAux));
        }
        return ListToSetConverter.convertListToSet(bookResponseDTO);
    }

}
