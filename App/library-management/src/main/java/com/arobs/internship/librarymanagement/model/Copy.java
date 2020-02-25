package com.arobs.internship.librarymanagement.model;

import com.arobs.internship.librarymanagement.model.enums.CopyCondition;
import com.arobs.internship.librarymanagement.model.enums.CopyStatus;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "copy")
public class Copy {

    @Id
    @GeneratedValue
    @Column(name = "copy_id")
    private int id;

    @Column(nullable = false, length = 20, name = "isbn")
    private String isbn;

    @Column(nullable = false, length = 50, name = "Condition_Book")
    @Enumerated(EnumType.STRING)
    private CopyCondition copyCondition;

    @Column(nullable = false, length = 50, name = "Status")
    @Enumerated(EnumType.STRING)
    private CopyStatus copyStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToMany(mappedBy = "copy")
    private Set<BookRent> bookRents;

    public Copy() {
    }

    public Copy(String isbn, CopyCondition copyCondition, CopyStatus copyStatus, Book book, Set<BookRent> bookRents) {
        this.isbn = isbn;
        this.copyCondition = copyCondition;
        this.copyStatus = copyStatus;
        this.book = book;
        this.bookRents = bookRents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public CopyCondition getCopyCondition() {
        return copyCondition;
    }

    public void setCopyCondition(CopyCondition copyCondition) {
        this.copyCondition = copyCondition;
    }

    public CopyStatus getCopyStatus() {
        return copyStatus;
    }

    public void setCopyStatus(CopyStatus copyStatus) {
        this.copyStatus = copyStatus;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Set<BookRent> getBookRents() {
        return bookRents;
    }

    public void setBookRents(Set<BookRent> bookRents) {
        this.bookRents = bookRents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Copy copy = (Copy) o;
        return id == copy.id &&
                isbn.equals(copy.isbn) &&
                copyCondition == copy.copyCondition &&
                copyStatus == copy.copyStatus &&
                book.equals(copy.book) &&
                Objects.equals(bookRents, copy.bookRents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, copyCondition, copyStatus, book, bookRents);
    }
}
