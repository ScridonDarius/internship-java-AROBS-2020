package com.arobs.internship.librarymanagement.controller.api;

import com.arobs.internship.librarymanagement.controller.api.request.BookRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.request.CopyRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.CopyResponseDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.model.Copy;
import com.arobs.internship.librarymanagement.service.impl.CopyServiceImpl;
import com.arobs.internship.librarymanagement.service.mapperConverter.BookMapperConverter;
import com.arobs.internship.librarymanagement.service.mapperConverter.CopyMapperConvertor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/copy", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CopyController {

    private final CopyServiceImpl copyService;

    public CopyController(CopyServiceImpl copyService) {
        this.copyService = copyService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CopyResponseDTO> createBook(@RequestParam String title,
                                           @RequestParam String author,
                                           @RequestBody CopyRegistrationDTO request) {
        CopyResponseDTO copyResponseDTO;

        copyResponseDTO = CopyMapperConvertor.generateDTOResponseFromEntity(getCopyService().saveCopy(request, title, author));

        return new ResponseEntity<>(copyResponseDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/retrieveTags", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<CopyResponseDTO>> retrieveAll(
            @RequestParam String author,
            @RequestParam String title
    ) {
        Set<CopyResponseDTO> copyResponseDTO = getCopyService().findCopyByBook(author, title).stream().
                map(copy -> new CopyResponseDTO(copy.getId(), copy.getIsbn(), copy.getCopyCondition(), copy.getCopyStatus(), CopyMapperConvertor.generateBookCopyFromCopy(copy.getBook()))).collect(Collectors.toSet());


        return copyResponseDTO != null
                ? new ResponseEntity<>(copyResponseDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public CopyServiceImpl getCopyService() {
        return copyService;
    }
}
