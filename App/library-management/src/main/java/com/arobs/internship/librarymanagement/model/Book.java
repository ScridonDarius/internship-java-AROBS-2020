package com.arobs.internship.librarymanagement.model;

import com.arobs.internship.librarymanagement.model.enums.BookRentStatus;
import com.arobs.internship.librarymanagement.model.enums.BookStatus;

import javax.persistence.*;
import java.util.HashSet;
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

    @Column(nullable = false, length = 50, name = "Status")
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
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

    public Book(String title, String author, String description, Set<Tag> tags, Set<Copy> copies, Set<BookRent> bookRents, Set<RentRequest> rentRequests, BookStatus bookStatus) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.tags = tags;
        this.copies = copies;
        this.bookRents = bookRents;
        this.rentRequests = rentRequests;
        this.bookStatus = bookStatus;
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

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
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
}