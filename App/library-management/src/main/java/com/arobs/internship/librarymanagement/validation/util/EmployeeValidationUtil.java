package com.arobs.internship.librarymanagement.validation.util;

import com.arobs.internship.librarymanagement.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Component
public class EmployeeValidationUtil {

    private final Logger logger = LoggerFactory.getLogger(EmployeeValidationUtil.class);

    @Autowired
    private EmployeeService employeeService;

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
