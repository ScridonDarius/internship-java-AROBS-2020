package com.arobs.internship.librarymanagement.repository.jdbc;

import com.arobs.internship.librarymanagement.model.Tag;
import com.arobs.internship.librarymanagement.model.mapper.TagMapper;
import com.arobs.internship.librarymanagement.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class TagRepositoryJdbcImpl implements TagRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public TagRepositoryJdbcImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Tag findByTagName(String tagName) throws SQLException {
        return jdbcTemplate.queryForObject("SELECT * FROM tag WHERE tag_name = ?", new Object[] { tagName }, new TagMapper());
    }

    @Override
    public int createTag(Tag tag) throws SQLException {
        return jdbcTemplate.update("INSERT INTO tag(tag_name) VALUES (?)", tag.getTagName());
    }
}
