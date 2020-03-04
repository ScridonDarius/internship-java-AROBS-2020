package com.arobs.internship.librarymanagement.controller.api;

import com.arobs.internship.librarymanagement.controller.api.request.BookRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.CopyRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.CopyResponseDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.service.impl.CopyServiceImpl;
import com.arobs.internship.librarymanagement.service.mapperConverter.BookMapperConverter;
import com.arobs.internship.librarymanagement.service.mapperConverter.CopyMapperConvertor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/copy", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CopyController {

    private final CopyServiceImpl copyService;

    public CopyController(CopyServiceImpl copyService) {
        this.copyService = copyService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CopyResponseDTO> createBook(@RequestParam String title, String author,
                                                      @RequestBody CopyRegistrationDTO request) {
        CopyResponseDTO copyResponseDTO;

            copyResponseDTO = CopyMapperConvertor.generateDTOResponseFromEntity(getCopyService().saveCopy(request, title, author));

        return new ResponseEntity<>(copyResponseDTO, HttpStatus.CREATED);
    }

    public CopyServiceImpl getCopyService() {
        return copyService;
    }
}
