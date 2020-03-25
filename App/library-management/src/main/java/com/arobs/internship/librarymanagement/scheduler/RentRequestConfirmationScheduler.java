package com.arobs.internship.librarymanagement.scheduler;

import com.arobs.internship.librarymanagement.service.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentRequestConfirmationScheduler {

    private static final Logger LOG = LoggerFactory.getLogger(BookRentScheduler.class);

    private static final long MILLIS_PER_MINUTE = 60000;
    private static final long MINUTE = 10;

    @Autowired
    private SchedulerService schedulerService;

    // @Scheduled(fixedRate = MINUTE * MILLIS_PER_MINUTE)
    public void checkEmailDateIsPassed() {
        getSchedulerService().emailDatePassed();
    }


    //@Scheduled(fixedRate = MINUTE * MILLIS_PER_MINUTE)
    public void checkCopyAvailableAndSendMailForConfirmation() {
        getSchedulerService().checkCopyAvailability();
    }

    public SchedulerService getSchedulerService() {
        return schedulerService;
    }
}