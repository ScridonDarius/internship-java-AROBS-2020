package com.arobs.internship.librarymanagement.service.mapperConverter;

import com.arobs.internship.librarymanagement.controller.api.request.BookRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.TagRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookResponseDTO;
import com.arobs.internship.librarymanagement.model.Book;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;


public class BookMapperConverter {

    static ModelMapper modelMapper = new ModelMapper();

    public static BookResponseDTO generateDTOResponseFromEntity(Book book) {
        return modelMapper.map(book, BookResponseDTO.class);
    }

    public static Book generateEntityFromDTOResponse(BookResponseDTO bookResponseDTO) {
        return modelMapper.map(bookResponseDTO, Book.class);
    }

    public static BookRegistrationDTO generateDTORegistrationFromEntity(Book book) {
        return modelMapper.map(book, BookRegistrationDTO.class);
    }

    public static Book generateEntityFromDTORegistration(BookRegistrationDTO bookRegistrationDTO) {
        return modelMapper.map(bookRegistrationDTO, Book.class);
    }
}
