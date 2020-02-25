package com.arobs.internship.librarymanagement.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue
    @Column(name = "tag_id")
    private Long id;

    @Column(name = "tag_name", nullable = false, length = 50, unique = true)
    private String tagName;

    public Tag() {
    }

    public Tag(Long id, String tagName) {
        this.id = id;
        this.tagName = tagName;
    }

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return id.equals(tag.id) &&
                tagName.equals(tag.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tagName);
    }
}


