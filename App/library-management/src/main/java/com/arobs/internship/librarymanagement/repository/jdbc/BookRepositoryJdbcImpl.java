package com.arobs.internship.librarymanagement.repository.jdbc;

import com.arobs.internship.librarymanagement.model.Book;
import com.arobs.internship.librarymanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class BookRepositoryJdbcImpl implements BookRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookRepositoryJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Book insertBook(Book book){


                return null;
    }
}
