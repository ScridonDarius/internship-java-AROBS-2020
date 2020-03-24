package com.arobs.internship.librarymanagement.mapperConverter;

import com.arobs.internship.librarymanagement.controller.api.request.RentRequestRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.RentRequestResponseDTO;
import com.arobs.internship.librarymanagement.model.RentRequest;
import org.modelmapper.ModelMapper;

public class RentRequestMapperConverter {
    private static ModelMapper modelMapper = new ModelMapper();

    public static RentRequestRegistrationDTO generateRegistrationDTOFromEntity(RentRequest rentRequest) {
        return modelMapper.map(rentRequest, RentRequestRegistrationDTO.class);
    }

    public static RentRequest generateEntityFromRegistrationDTO(RentRequestRegistrationDTO rentRequestRegistration) {
        return modelMapper.map(rentRequestRegistration, RentRequest.class);
    }

    public static RentRequestResponseDTO generateResponseDTOFromEntity(RentRequest rentRequest) {
        return modelMapper.map(rentRequest, RentRequestResponseDTO.class);
    }
}
