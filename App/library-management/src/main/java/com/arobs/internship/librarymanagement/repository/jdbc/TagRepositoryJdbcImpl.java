package com.arobs.internship.librarymanagement.repository.jdbc;

import com.arobs.internship.librarymanagement.model.Tag;
import com.arobs.internship.librarymanagement.repository.TagRepository;
import com.arobs.internship.librarymanagement.repository.jdbc.mapper.TagMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class TagRepositoryJdbcImpl implements TagRepository {

    private final JdbcTemplate jdbcTemplate;

    public TagRepositoryJdbcImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Tag findByTagName(String tagName) {
        jdbcTemplate.queryForObject("SELECT * FROM tag WHERE tag_name = ?", new Object[]{tagName}, new TagMapper());
        return null;
    }

    @Override
    public Tag save(Tag tag) {
        int id = jdbcTemplate.update("INSERT INTO tag(tag_name) VALUES (?)", tag.getTagName());

        return null;
    }

    @Override
    public void update(Tag tag) {
        jdbcTemplate.update("UPDATE tag set tag_name = ? WHERE id =?", tag.getTagName(), tag.getId());
    }

    @Override
    public void delete(Tag tag) {
        jdbcTemplate.update("DELETE FROM tag WHERE tag_name = ?", tag.getTagName());
    }

    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query("SELECT * FROM tag", new TagMapper());
    }
}
