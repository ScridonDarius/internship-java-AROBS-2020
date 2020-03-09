package com.arobs.internship.librarymanagement.service.mapperConverter;

import com.arobs.internship.librarymanagement.controller.api.request.BookRentRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookRentResponseDTO;
import com.arobs.internship.librarymanagement.model.BookRent;
import org.modelmapper.ModelMapper;

public class BookRentMapperConverter {

    static ModelMapper modelMapper = new ModelMapper();

    public static BookRent generateEntityFromRegistration(BookRentRegistrationDTO bookRentRegistrationDTO) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(bookRentRegistrationDTO, BookRent.class);
    }

    public static BookRentResponseDTO generateResponseFromEntity(BookRent bookRent) {

        return modelMapper.map(bookRent, BookRentResponseDTO.class);
    }
}
