package com.arobs.internship.librarymanagement.service.mapperConverter;

import com.arobs.internship.librarymanagement.controller.api.request.BookRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.BookUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookResponseDTO;
import com.arobs.internship.librarymanagement.model.Book;
import org.modelmapper.ModelMapper;


public class BookMapperConverter {

    static ModelMapper modelMapper = new ModelMapper();

    public static BookResponseDTO generateDTOResponseFromEntity(Book book) {
        return modelMapper.map(book, BookResponseDTO.class);
    }

    public static BookDTO generateDTOFromEntity(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    public static BookUpdateDTO generateUpdateDTOFromEntity(Book book) {
        return modelMapper.map(book, BookUpdateDTO.class);
    }

    public static Book generateEntityFromDTORegistration(BookRegistrationDTO bookRegistrationDTO) {
        return modelMapper.map(bookRegistrationDTO, Book.class);
    }
}
