package com.arobs.internship.librarymanagement.scheduler;

import com.arobs.internship.librarymanagement.controller.api.request.BookRentUpdateDTO;
import com.arobs.internship.librarymanagement.exception.FoundException;
import com.arobs.internship.librarymanagement.model.BookRent;
import com.arobs.internship.librarymanagement.model.enums.BookRentStatus;
import com.arobs.internship.librarymanagement.service.BookRentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Set;


@Component
public class BookRentScheduler {

    private static final Logger LOG = LoggerFactory.getLogger(BookRentScheduler.class);

    private static final long MILLIS_PER_MINUTE = 60000;

    @Autowired
    private BookRentService bookRentService;

    @Scheduled(fixedRate = 30 * MILLIS_PER_MINUTE)
    public void checkRentTime() throws FoundException {
        final Set<BookRent> bookRents = getBookRentService().getBookRentsOrderedByDate();

        if (CollectionUtils.isEmpty(bookRents)) {
            return;
        }

        bookRents.forEach(bookRent -> {
            if (bookRent.getRentalDate().plusMonths(1).compareTo(LocalDateTime.now()) < 0) {
                System.out.println("STATUS CHANGE");
                bookRent.setBookRentStatus(BookRentStatus.LATE);

                BookRentUpdateDTO bookRentUpdateDTO = new BookRentUpdateDTO();
                bookRentUpdateDTO.setBookRentStatus(BookRentStatus.LATE);

                try {
                    getBookRentService().update(bookRentUpdateDTO, bookRent.getId());
                } catch (FoundException e) {
                    LOG.error("", e);
                }
            }
        });
    }

    public BookRentService getBookRentService() {
        return bookRentService;
    }
}
