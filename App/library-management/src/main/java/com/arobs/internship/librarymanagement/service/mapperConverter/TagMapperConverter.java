package com.arobs.internship.librarymanagement.service.mapperConverter;

import com.arobs.internship.librarymanagement.controller.api.request.TagRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.TagUpdateDTO;
import com.arobs.internship.librarymanagement.controller.api.response.TagBookResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.TagResponseDTO;
import com.arobs.internship.librarymanagement.model.Tag;
import org.modelmapper.ModelMapper;

public class TagMapperConverter {

    static ModelMapper modelMapper = new ModelMapper();

    public static TagResponseDTO generateDTOResponseFromEntity(Tag tag) {
        return modelMapper.map(tag, TagResponseDTO.class);
    }

    public static Tag generateEntityFromDTOResponse(TagResponseDTO tagResponseDTO) {
        return modelMapper.map(tagResponseDTO, Tag.class);
    }


    public static TagRegistrationDTO generateRegistrationFromTagBookDTO(TagBookResponseDTO book) {
        return modelMapper.map(book, TagRegistrationDTO.class);
    }

    public static Tag generateEntityFromTagBookDTO(TagBookResponseDTO book) {
        return modelMapper.map(book, Tag.class);
    }

    public static Tag generateEntityFromDTORegistration(TagRegistrationDTO tagRegistrationDTO) {
        return modelMapper.map(tagRegistrationDTO, Tag.class);
    }

    public static TagRegistrationDTO generateRegistrationFromResponse(TagResponseDTO tagResponseDTO) {
        return modelMapper.map(tagResponseDTO, TagRegistrationDTO.class);
    }

    public static TagRegistrationDTO generateDTORegistrationFromEntity(Tag tag) {
        return modelMapper.map(tag, TagRegistrationDTO.class);
    }

    public static Tag generateEntityFromDTOUpdate(TagUpdateDTO tagUpdateDTO) {
        return modelMapper.map(tagUpdateDTO, Tag.class);
    }

    public static TagUpdateDTO generateDTOUpdateFromEntity(Tag tag) {
        return modelMapper.map(tag, TagUpdateDTO.class);
    }
}
