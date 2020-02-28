package com.arobs.internship.librarymanagement.repository.jdbc.mapper;

import com.arobs.internship.librarymanagement.model.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagMapper implements RowMapper<Tag> {

    @Override
    public Tag mapRow(ResultSet resultSet, int i) throws SQLException {
        Tag tag = new Tag();
        tag.setId(resultSet.getInt("tag_id"));
        tag.setTagName(resultSet.getString("tag_name"));

        return tag;
    }
}
