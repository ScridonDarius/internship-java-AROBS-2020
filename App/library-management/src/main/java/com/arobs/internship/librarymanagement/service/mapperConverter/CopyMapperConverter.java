package com.arobs.internship.librarymanagement.service.mapperConverter;

import com.arobs.internship.librarymanagement.controller.api.request.CopyRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.CopyUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookCopyDTO;
import com.arobs.internship.librarymanagement.controller.api.response.CopyResponseDTO;
import com.arobs.internship.librarymanagement.model.Book;
import com.arobs.internship.librarymanagement.model.Copy;
import org.modelmapper.ModelMapper;

public class CopyMapperConverter {

    static ModelMapper modelMapper = new ModelMapper();

    public static CopyResponseDTO generateDTOResponseFromEntity(Copy copy) {
        return modelMapper.map(copy, CopyResponseDTO.class);
    }

    public static CopyUpdateDTO generateUpdateDTOeFromEntity(Copy copy) {
        return modelMapper.map(copy, CopyUpdateDTO.class);
    }

    public static Copy generateEntityFromDTORegistration(CopyRegistrationDTO copyRegistrationDTO) {
        return modelMapper.map(copyRegistrationDTO, Copy.class);
    }

    public static BookCopyDTO generateDTOFromEntity(Copy copy) {
        return modelMapper.map(copy, BookCopyDTO.class);
    }

    public static BookCopyDTO generateBookCopyFromCopy(Book book) {
        return modelMapper.map(book, BookCopyDTO.class);
    }
}
