package com.arobs.internship.librarymanagement.repository.factory.hibernate;

import com.arobs.internship.librarymanagement.repository.BookRepository;
import com.arobs.internship.librarymanagement.repository.EmployeeRepository;
import com.arobs.internship.librarymanagement.repository.TagRepository;
import com.arobs.internship.librarymanagement.repository.factory.RepositoryFactory;
import com.arobs.internship.librarymanagement.repository.hibernate.BookRepositoryHibernateImpl;
import com.arobs.internship.librarymanagement.repository.hibernate.EmployeeRepositoryHibernateImpl;
import com.arobs.internship.librarymanagement.repository.hibernate.TagRepositoryHibernateImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class HibernateRepositoryFactory extends RepositoryFactory {

    @Autowired
    private EmployeeRepositoryHibernateImpl employeeRepositoryHibernate;

    @Autowired
    private BookRepositoryHibernateImpl bookRepositoryHibernate;

    @Autowired
    private TagRepositoryHibernateImpl tagRepositoryHibernate;

    @Override
    public EmployeeRepository getEmployeeRepository() {
        return employeeRepositoryHibernate;
    }

    @Override
    public BookRepository getBookRepository() {
        return bookRepositoryHibernate;
    }

    @Override
    public TagRepository getTagRepository() {
        return tagRepositoryHibernate;
    }
}
