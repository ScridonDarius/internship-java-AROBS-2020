package com.arobs.internship.librarymanagement.model;

import com.arobs.internship.librarymanagement.model.enums.BookRentStatus;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;
//
//@Entity
//@Table(name = "book_request")
public class BookRequest {
    private Long id;
    private String title;
    private String author;
    private String publishingCompany;
    private int copyNumber;
    private BigDecimal totalCost;
    private BookRentStatus bookRentStatus;

    public BookRequest() {
    }

    public BookRequest(Long id, String title, String author, String publishingCompany, int copyNumber, BigDecimal totalCost, BookRentStatus bookRentStatus) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishingCompany = publishingCompany;
        this.copyNumber = copyNumber;
        this.totalCost = totalCost;
        this.bookRentStatus = bookRentStatus;
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

    public String getPublishingCompany() {
        return publishingCompany;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }

    public int getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(int copyNumber) {
        this.copyNumber = copyNumber;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BookRentStatus getBookRentStatus() {
        return bookRentStatus;
    }

    public void setBookRentStatus(BookRentStatus bookRentStatus) {
        this.bookRentStatus = bookRentStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookRequest that = (BookRequest) o;
        return copyNumber == that.copyNumber &&
                id.equals(that.id) &&
                title.equals(that.title) &&
                author.equals(that.author) &&
                publishingCompany.equals(that.publishingCompany) &&
                totalCost.equals(that.totalCost) &&
                bookRentStatus == that.bookRentStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, publishingCompany, copyNumber, totalCost, bookRentStatus);
    }
}
