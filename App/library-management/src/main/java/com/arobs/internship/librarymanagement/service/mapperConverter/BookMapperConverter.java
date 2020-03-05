package com.arobs.internship.librarymanagement.service.mapperConverter;

import com.arobs.internship.librarymanagement.controller.api.request.BookRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.BookUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookResponseDTO;
import com.arobs.internship.librarymanagement.model.Book;
import org.modelmapper.ModelMapper;


public class BookMapperConverter {

    static ModelMapper modelMapper = new ModelMapper();

    public static BookResponseDTO generateDTOResponseFromEntity(Book book) {
        return modelMapper.map(book, BookResponseDTO.class);
    }

    public static Book generateEntityFromDTOResponse(BookResponseDTO bookResponseDTO) {
        return modelMapper.map(bookResponseDTO, Book.class);
    }

    public static BookResponseDTO generateResponseFromUpdate(BookUpdateDTO bookUpdateDTO) {
        return modelMapper.map(bookUpdateDTO, BookResponseDTO.class);
    }

    public static BookUpdateDTO generateUpdateDTOFromEntity(Book book) {
        return modelMapper.map(book, BookUpdateDTO.class);
    }


    public static BookRegistrationDTO generateDTORegistrationFromEntity(Book book) {
        return modelMapper.map(book, BookRegistrationDTO.class);
    }

    public static Book generateEntityFromDTORegistration(BookRegistrationDTO bookRegistrationDTO) {
        return modelMapper.map(bookRegistrationDTO, Book.class);
    }
}
