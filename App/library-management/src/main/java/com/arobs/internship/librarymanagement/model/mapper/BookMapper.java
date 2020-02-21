package com.arobs.internship.librarymanagement.model.mapper;


import com.arobs.internship.librarymanagement.model.Book;
import com.arobs.internship.librarymanagement.model.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong("id"));
        book.setAuthor(resultSet.getString("author"));
        book.setTitle(resultSet.getString("title"));
        book.setDescription(resultSet.getString("description"));

        return book;
    }
}
