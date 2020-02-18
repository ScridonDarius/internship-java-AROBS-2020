package com.arobs.internship.librarymanagement.model;

import java.util.List;
import java.util.Objects;

public class Book {
    private Long id;
    private String title;
    private String author;
    private String description;
    private List<Tag> tags;

    public Book() {
    }

    public Book(Long id, String title, String author, String description, List<Tag> tags) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.tags = tags;
    }

    public Book(String title, String author, String description, List<Tag> tags) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id) &&
                title.equals(book.title) &&
                author.equals(book.author) &&
                Objects.equals(description, book.description) &&
                tags.equals(book.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, description, tags);
    }
}
