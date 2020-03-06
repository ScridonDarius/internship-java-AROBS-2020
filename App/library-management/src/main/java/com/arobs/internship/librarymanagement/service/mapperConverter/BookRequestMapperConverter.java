package com.arobs.internship.librarymanagement.service.mapperConverter;

import com.arobs.internship.librarymanagement.controller.api.request.BookRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.BookRequestRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.BookRequestUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookRequestResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.CopyResponseDTO;
import com.arobs.internship.librarymanagement.model.Book;
import com.arobs.internship.librarymanagement.model.BookRequest;
import com.arobs.internship.librarymanagement.model.Copy;
import org.modelmapper.ModelMapper;

public class BookRequestMapperConverter {

    static ModelMapper modelMapper = new ModelMapper();

    public static BookRequestResponseDTO generateDTOResponseFromEntity(BookRequest bookRequest) {
        return modelMapper.map(bookRequest, BookRequestResponseDTO.class);
    }

    public static BookRequest generateEntityFromDTORegistration(BookRequestRegistrationDTO bookRegistrationDTO) {
        return modelMapper.map(bookRegistrationDTO, BookRequest.class);
    }

    public static BookRequestUpdateDTO generateDTOUpdateFromEntity(BookRequest bookRequest) {
        return modelMapper.map(bookRequest, BookRequestUpdateDTO.class);
    }
}
