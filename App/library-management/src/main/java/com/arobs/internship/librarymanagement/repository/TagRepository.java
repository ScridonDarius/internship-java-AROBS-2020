package com.arobs.internship.librarymanagement.repository;

import com.arobs.internship.librarymanagement.model.Tag;

import java.util.List;

public interface TagRepository {

    Tag createTag(Tag tag);

    List<Tag> findByTagName(String tagName);

    void updateTag(Tag tag);

    void deleteTag(Tag tag);

    List<Tag> findAll();
}
