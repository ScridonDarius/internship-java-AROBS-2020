package com.arobs.internship.librarymanagement.repository.factory.hibernate;

import com.arobs.internship.librarymanagement.repository.EmployeeRepository;
import com.arobs.internship.librarymanagement.repository.factory.RepositoryFactory;
import com.arobs.internship.librarymanagement.repository.hibernate.EmployeeRepositoryHibernateImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class HibernateRepositoryFactory extends RepositoryFactory {

    @Autowired
    private EmployeeRepositoryHibernateImpl  employeeRepositoryHibernate;

    @Override
    public EmployeeRepository getEmployeeRepository() {
        return employeeRepositoryHibernate;
    }

}
