package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.BookRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.TagResponseDTO;
import com.arobs.internship.librarymanagement.model.Book;
import com.arobs.internship.librarymanagement.model.Tag;
import com.arobs.internship.librarymanagement.repository.BookRepository;
import com.arobs.internship.librarymanagement.repository.factory.RepositoryFactory;
import com.arobs.internship.librarymanagement.repository.jdbc.mapper.TagMapper;
import com.arobs.internship.librarymanagement.service.BookService;
import com.arobs.internship.librarymanagement.service.mapperConverter.BookMapperConvertor;
import com.arobs.internship.librarymanagement.service.mapperConverter.TagMapperConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.HashSet;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    private RepositoryFactory repositoryFactory;

    @Autowired
    private TagServiceImpl tagService;

//    @Autowired
//    private TagRepository tagRepository;

    @PostConstruct
    public void init() {
        RepositoryFactory factory = repositoryFactory.getInstance();
        bookRepository = factory.getBookRepository();
    }

    @Override
    @Transactional
    public BookResponseDTO insertBook(BookRegistrationDTO request) {

        Book book = BookMapperConvertor.generateEntityFromDTORegistration(request);
        if(!request.getTags().isEmpty()){
            insertBookTag(book, request.getTags());
        } else {
            book = bookRepository.save(book);
        }

return BookMapperConvertor.generateDTOResponseFromEntity(book);


    }

    private Book insertBookTag(Book book , Set<TagResponseDTO> tags) {

        Set<TagResponseDTO> tagResponseDTO = this.tagService.retrieveAll();
        Set<Tag> tagSet = new HashSet<>();
        Tag tagDB = null;
        BookRegistrationDTO registrationDTO = null;
        for (TagResponseDTO tag : tags) {
            if (tagResponseDTO.contains(tags)) {
                tagDB = TagMapperConverter.generateEntityFromDTOResponse(tagService.retrieveByTagName(tags.toString()));
                tagSet.add(tagDB);
            } else {

                this.tagService.getTagRepository().createTag(tagDB);
                tagDB = TagMapperConverter.generateEntityFromDTOResponse(tagService.retrieveByTagName(tags.toString()));
                tagSet.add(tagDB);
            }
            book.setTags(tagSet);
        }
        book = bookRepository.save(book);

        return book;


    }

    @Override
    @Transactional
    public BookResponseDTO retrieveBookByAuthorAndTitle(String author, String title) {
        return BookMapperConvertor.generateDTOResponseFromEntity(bookRepository.findBook(author, title));
    }
    }
