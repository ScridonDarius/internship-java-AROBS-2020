package com.arobs.internship.librarymanagement.repository;

import com.arobs.internship.librarymanagement.model.Tag;

import java.awt.print.Pageable;
import java.sql.SQLException;
import java.util.List;

public interface TagRepository {

    int createTag(Tag tag) ;

    Tag findByTagName(String tagName) ;

    boolean updateTag(String tagName, String newTag);

    boolean deleteTag(String tagName);

    List<Tag> findAll();

}
