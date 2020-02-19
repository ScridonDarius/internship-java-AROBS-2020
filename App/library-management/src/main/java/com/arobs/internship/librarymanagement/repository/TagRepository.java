package com.arobs.internship.librarymanagement.repository;

import com.arobs.internship.librarymanagement.model.Tag;

import java.awt.print.Pageable;
import java.sql.SQLException;
import java.util.List;

public interface TagRepository {

    int createTag(Tag tag) throws SQLException;

    Tag findByTagName(String tagName) throws SQLException;

    boolean updateTag(String tagName, String newTag);

    boolean deleteTag(String tagName);

    List<Tag> findAll();

}
