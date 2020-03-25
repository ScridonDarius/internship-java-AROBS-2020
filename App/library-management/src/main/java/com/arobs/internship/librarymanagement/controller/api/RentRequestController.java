package com.arobs.internship.librarymanagement.controller.api;

import com.arobs.internship.librarymanagement.controller.api.request.RentRequestRegistrationDTO;
import com.arobs.internship.librarymanagement.controller.api.response.BookRentResponseDTO;
import com.arobs.internship.librarymanagement.controller.api.response.RentRequestResponseDTO;
import com.arobs.internship.librarymanagement.mapperConverter.BookMapperConverter;
import com.arobs.internship.librarymanagement.mapperConverter.CopyMapperConverter;
import com.arobs.internship.librarymanagement.mapperConverter.EmployeeMapperConverter;
import com.arobs.internship.librarymanagement.model.RentRequest;
import com.arobs.internship.librarymanagement.model.enums.BookRentStatus;
import com.arobs.internship.librarymanagement.model.enums.RentRequestStatus;
import com.arobs.internship.librarymanagement.service.RentRequestService;
import com.arobs.internship.librarymanagement.service.impl.RentRequestServiceImpl;
import com.arobs.internship.librarymanagement.mapperConverter.RentRequestMapperConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/rentRequest", produces = {MediaType.APPLICATION_JSON_VALUE})
public class RentRequestController {

    @Autowired
    private RentRequestService bookRentService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RentRequestResponseDTO> create(@RequestBody RentRequestRegistrationDTO request) {
        RentRequestResponseDTO rentRequestResponse = RentRequestMapperConverter.generateResponseDTOFromEntity(getBookRentService().save(request));

        return new ResponseEntity<>(rentRequestResponse, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/retrieveById/{rentRequestId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RentRequestResponseDTO> retrieveById(@PathVariable("rentRequestId") Integer rentRequestId) {
        RentRequestResponseDTO rentRequestResponseDTO;

        try {
            rentRequestResponseDTO = RentRequestMapperConverter.generateResponseDTOFromEntity(getBookRentService().retrieveById(rentRequestId));
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Processing fail. This rentRequest doesn't exist!");
        }
        return new ResponseEntity<>(rentRequestResponseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/retrieveAll", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<RentRequestResponseDTO>> retrieveAll() {

        Set<RentRequestResponseDTO> rentRequests = getBookRentService()
                .retrieveAll()
                .stream()
                .map(book -> new RentRequestResponseDTO(book.getId(), book.getRequestDate(),book.getRentRequestStatus()
                        ,EmployeeMapperConverter.generateBookRequestEmployeeFromEntity(book.getEmployee()),
                        BookMapperConverter.generateDTOFromEntity(book.getBook())))
                .collect(Collectors.toSet());

        return rentRequests != null
                ? new ResponseEntity<>(rentRequests, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/retrieveByStatus", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<RentRequestResponseDTO>> retrieveByStatus(@RequestParam RentRequestStatus rentRequestStatus) {

        Set<RentRequestResponseDTO> rentRequests = getBookRentService().retrieveByStatus(rentRequestStatus)
                .stream()
                .map(book -> new RentRequestResponseDTO(book.getId(), book.getRequestDate(),book.getRentRequestStatus()
                        ,EmployeeMapperConverter.generateBookRequestEmployeeFromEntity(book.getEmployee()),
                        BookMapperConverter.generateDTOFromEntity(book.getBook())))
                .collect(Collectors.toSet());

        return rentRequests != null
                ? new ResponseEntity<>(rentRequests, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public RentRequestService getBookRentService() {
        return bookRentService;
    }
}
