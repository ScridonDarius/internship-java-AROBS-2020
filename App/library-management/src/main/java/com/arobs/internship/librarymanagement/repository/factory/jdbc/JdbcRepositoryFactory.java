package com.arobs.internship.librarymanagement.repository.factory.jdbc;

import com.arobs.internship.librarymanagement.repository.BookRepository;
import com.arobs.internship.librarymanagement.repository.EmployeeRepository;
import com.arobs.internship.librarymanagement.repository.TagRepository;
import com.arobs.internship.librarymanagement.repository.factory.RepositoryFactory;
import com.arobs.internship.librarymanagement.repository.hibernate.BookRepositoryHibernateImpl;
import com.arobs.internship.librarymanagement.repository.hibernate.TagRepositoryHibernateImpl;
import com.arobs.internship.librarymanagement.repository.jdbc.EmployeeRepositoryJdbcImpl;
import com.arobs.internship.librarymanagement.repository.jdbc.TagRepositoryJdbcImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JdbcRepositoryFactory extends RepositoryFactory {

    @Autowired
    private EmployeeRepositoryJdbcImpl employeeRepositoryJdbc;

    @Autowired
    private TagRepositoryJdbcImpl tagRepositoryJdbc;

    @Override
    public EmployeeRepository getEmployeeRepository() {
        return employeeRepositoryJdbc;
    }


    @Override
    public BookRepository getBookRepository() {
        return null;
    }

    @Override
    public TagRepository getTagRepository() {
        return tagRepositoryJdbc;
    }
}