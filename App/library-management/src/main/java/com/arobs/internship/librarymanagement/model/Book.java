package com.arobs.internship.librarymanagement.model;

import java.util.Objects;

public class Book {

    private Long id;
    private String title;
    private String author;
    private String description;
    private Tag tag;

    public Book() {
    }

    public Book(Long id, String title, String author, String description, Tag tag) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.tag = tag;
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

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
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
                tag.equals(book.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, description, tag);
    }
}
