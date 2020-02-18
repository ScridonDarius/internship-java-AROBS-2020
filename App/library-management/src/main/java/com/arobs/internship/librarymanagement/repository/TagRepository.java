package com.arobs.internship.librarymanagement.repository;

import com.arobs.internship.librarymanagement.model.Tag;

import java.sql.SQLException;

public interface TagRepository {

    int createTag(Tag tag) throws SQLException;

    Tag findByTagName(String tagName) throws SQLException;

}
