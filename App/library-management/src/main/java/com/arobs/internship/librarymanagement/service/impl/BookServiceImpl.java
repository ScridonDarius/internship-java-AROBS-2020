package com.arobs.internship.librarymanagement.service.impl;

import com.arobs.internship.librarymanagement.controller.api.request.BookRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookResponseDTO;
import com.arobs.internship.librarymanagement.repository.BookRepository;
import com.arobs.internship.librarymanagement.repository.factory.RepositoryFactory;
import com.arobs.internship.librarymanagement.service.BookService;
import com.arobs.internship.librarymanagement.service.mapperConverter.BookMapperConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    private RepositoryFactory repositoryFactory;

    @PostConstruct
    public void init() {
        RepositoryFactory factory = repositoryFactory.getInstance();
        bookRepository = factory.getBookRepository();
    }

    @Override
    @Transactional
    public BookResponseDTO insertBook(BookRegistrationDTO request) {
        bookRepository.save(BookMapperConvertor.generateEntityFromDTORegistration(request));

        return BookMapperConvertor.generateDTOResponseFromEntity(bookRepository.findBook(request.getAuthor(), request.getTitle()));
    }

    @Override
    @Transactional
    public BookResponseDTO retrieveBookByAuthorAndTitle(String author, String title) {
        return BookMapperConvertor.generateDTOResponseFromEntity(bookRepository.findBook(author, title));
    }
}
