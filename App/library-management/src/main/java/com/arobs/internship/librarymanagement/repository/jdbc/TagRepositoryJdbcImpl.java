package com.arobs.internship.librarymanagement.repository.jdbc;

import com.arobs.internship.librarymanagement.model.Tag;
import com.arobs.internship.librarymanagement.model.mapper.TagMapper;
import com.arobs.internship.librarymanagement.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.awt.print.Pageable;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TagRepositoryJdbcImpl implements TagRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public TagRepositoryJdbcImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Tag findByTagName(String tagName)  {
        return jdbcTemplate.queryForObject("SELECT * FROM tag WHERE tag_name = ?", new Object[] { tagName }, new TagMapper());
    }

    @Override
    public int createTag(Tag tag) {
        return jdbcTemplate.update("INSERT INTO tag(tag_name) VALUES (?)", tag.getTagName());
    }

    @Override
    public boolean updateTag(String tagName, String newTag) {
        return jdbcTemplate.update("UPDATE tag set tag_name = ? WHERE tag_name =?", newTag, tagName)>0;
    }

    @Override
    public boolean deleteTag(String tagName) {
        return jdbcTemplate.update("DELETE FROM tag WHERE tag_name = ?", tagName)>0;
    }

    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query("SELECT * FROM tag", new TagMapper());
    }
}
