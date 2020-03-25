package com.arobs.internship.librarymanagement.service;

import com.arobs.internship.librarymanagement.exception.FoundException;

import javax.transaction.Transactional;

public interface SchedulerService {

    void checkCopyAvailability();

    void emailDatePassed();

    @Transactional
    void employeeStatus();

    @Transactional
    void rentTime();
}
