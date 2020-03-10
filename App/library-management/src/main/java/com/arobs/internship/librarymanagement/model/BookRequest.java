package com.arobs.internship.librarymanagement.model;

import com.arobs.internship.librarymanagement.model.enums.BookRequestStatus;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "book_request")
public class BookRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_request_id")
    private int id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 50)
    private String author;

    @Column(nullable = false, length = 50, name = "publishing_company")
    private String publishingCompany;

    @Column(nullable = false, name = "number_copy")
    private int copyNumber;

    @Column(nullable = false, name = "total_cost")
    private BigDecimal totalCost;

    @Column(nullable = false, length = 50, name = "Status")
    @Enumerated(EnumType.STRING)
    private BookRequestStatus bookRequestStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public BookRequest() {
    }

    public BookRequest(String title, String author, String publishingCompany, int copyNumber, BigDecimal totalCost, BookRequestStatus bookRequestStatus, Employee employee) {
        this.title = title;
        this.author = author;
        this.publishingCompany = publishingCompany;
        this.copyNumber = copyNumber;
        this.totalCost = totalCost;
        this.bookRequestStatus = bookRequestStatus;
        this.employee = employee;
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

    public BookRequestStatus getBookRequestStatus() {
        return bookRequestStatus;
    }

    public void setBookRequestStatus(BookRequestStatus bookRequestStatus) {
        this.bookRequestStatus = bookRequestStatus;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
