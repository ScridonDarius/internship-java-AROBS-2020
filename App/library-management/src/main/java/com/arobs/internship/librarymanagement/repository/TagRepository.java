package com.arobs.internship.librarymanagement.repository;

import com.arobs.internship.librarymanagement.model.Tag;

import java.util.List;

public interface TagRepository {

    Tag save(Tag tag);

    Tag findByTagName(String tagName);

    void update(Tag tag);

    void delete(Tag tag);

    List<Tag> findAll();
}
