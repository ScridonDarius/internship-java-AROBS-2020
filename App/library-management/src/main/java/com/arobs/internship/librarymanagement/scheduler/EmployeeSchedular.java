package com.arobs.internship.librarymanagement.scheduler;

import com.arobs.internship.librarymanagement.controller.api.response.MailResponseDTO;
import com.arobs.internship.librarymanagement.model.Employee;
import com.arobs.internship.librarymanagement.model.RentRequest;
import com.arobs.internship.librarymanagement.model.enums.EmployeeStatus;
import com.arobs.internship.librarymanagement.service.EmployeeService;
import com.arobs.internship.librarymanagement.service.MailService;
import com.arobs.internship.librarymanagement.service.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Set;

@Component
public class EmployeeSchedular {

    private static final Logger LOG = LoggerFactory.getLogger(BookRentScheduler.class);

    private static final long MILLIS_PER_MINUTE = 60000;
    private static final long MINUTE = 30;

    @Autowired
    private SchedulerService schedulerService;


    //@Scheduled(fixedRate = MINUTE * MILLIS_PER_MINUTE)
    public void checkEmployeeStatus() {
        getSchedulerService().rentTime();
    }

    public SchedulerService getSchedulerService() {
        return schedulerService;
    }
}
