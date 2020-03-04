package com.arobs.internship.librarymanagement.service.mapperConverter;

import com.arobs.internship.librarymanagement.controller.api.request.CopyRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookCopyDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.CopyResponseDTO;
import com.arobs.internship.librarymanagement.model.Book;
import com.arobs.internship.librarymanagement.model.Copy;
import org.modelmapper.ModelMapper;

public class CopyMapperConvertor {

    static ModelMapper modelMapper = new ModelMapper();

    public static CopyResponseDTO generateDTOResponseFromEntity(Copy copy) {
        return modelMapper.map(copy, CopyResponseDTO.class);
    }

    public static Copy generateEntityFromDTORegistration(CopyRegistrationDTO copyRegistrationDTO) {
        return modelMapper.map(copyRegistrationDTO, Copy.class);
    }

    public static BookCopyDTO generateBookCopyFromCopy(Book book) {
        return modelMapper.map(book, BookCopyDTO.class);
    }
}
