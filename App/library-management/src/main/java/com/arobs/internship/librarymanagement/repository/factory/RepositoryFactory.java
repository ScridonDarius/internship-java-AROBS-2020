package com.arobs.internship.librarymanagement.repository.factory;

import com.arobs.internship.librarymanagement.repository.EmployeeRepository;
import com.arobs.internship.librarymanagement.repository.TagRepository;
import com.arobs.internship.librarymanagement.repository.factory.hibernate.HibernateRepositoryFactory;
import com.arobs.internship.librarymanagement.repository.factory.jdbc.JdbcRepositoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public abstract class RepositoryFactory {

    @Value("${datasource.type}")
    private String type;

    @Autowired
    private HibernateRepositoryFactory hibernateRepositoryFactory;

    @Autowired
    private JdbcRepositoryFactory jdbcRepositoryFactory;

    public String getType() {
        return type;
    }

    public RepositoryFactory() {
    }

    public RepositoryFactory getInstance() {
        switch (getType()) {
            case "HIBERNATE":
                return hibernateRepositoryFactory;
            case "JDBC":
                return jdbcRepositoryFactory;
            default:
                throw new IllegalArgumentException("Invalid factory");
        }
    }

    public abstract EmployeeRepository getEmployeeRepository();

    public abstract TagRepository getTagRepository();
}
