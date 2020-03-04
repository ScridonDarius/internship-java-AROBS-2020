package com.arobs.internship.librarymanagement.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 50)
    private String author;

    @Column(nullable = false, length = 100)
    private String description;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @Fetch(FetchMode.JOIN)
    @JoinTable(
            name = "book_tag",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private Set<Tag> tags;

    @OneToMany(mappedBy = "book")
    private Set<Copy> copies = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<BookRent> bookRents;

    @OneToMany(mappedBy = "book")
    private Set<RentRequest> rentRequests;

    public Book() {
    }

    public Book(String title, String author, String description, Set<Tag> tags, Set<Copy> copies, Set<BookRent> bookRents, Set<RentRequest> rentRequests) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.tags = tags;
        this.copies = copies;
        this.bookRents = bookRents;
        this.rentRequests = rentRequests;
    }

    public Book(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
    }
    public Book(String title, String author, String description, Set<Tag> tags) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Copy> getCopies() {
        return copies;
    }

    public void setCopies(Set<Copy> copies) {
        this.copies = copies;
    }

    public Set<BookRent> getBookRents() {
        return bookRents;
    }

    public void setBookRents(Set<BookRent> bookRents) {
        this.bookRents = bookRents;
    }

    public Set<RentRequest> getRentRequests() {
        return rentRequests;
    }

    public void setRentRequests(Set<RentRequest> rentRequests) {
        this.rentRequests = rentRequests;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Book book = (Book) o;
//        return id == book.id &&
//                title.equals(book.title) &&
//                author.equals(book.author) &&
//                description.equals(book.description) &&
//                tags.equals(book.tags) &&
//                copies.equals(book.copies) &&
//                bookRents.equals(book.bookRents) &&
//                rentRequests.equals(book.rentRequests);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, title, author, description, tags, copies, bookRents, rentRequests);
//    }
}