package com.arobs.internship.librarymanagement.model;

import com.arobs.internship.librarymanagement.model.enums.BookRentStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

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
    private BookRentStatus bookRentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public BookRequest() {
    }

    public BookRequest(String title, String author, String publishingCompany, int copyNumber, BigDecimal totalCost, BookRentStatus bookRentStatus, Employee employee) {
        this.title = title;
        this.author = author;
        this.publishingCompany = publishingCompany;
        this.copyNumber = copyNumber;
        this.totalCost = totalCost;
        this.bookRentStatus = bookRentStatus;
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

    public BookRentStatus getBookRentStatus() {
        return bookRentStatus;
    }

    public void setBookRentStatus(BookRentStatus bookRentStatus) {
        this.bookRentStatus = bookRentStatus;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookRequest that = (BookRequest) o;
        return id == that.id &&
                copyNumber == that.copyNumber &&
                title.equals(that.title) &&
                author.equals(that.author) &&
                publishingCompany.equals(that.publishingCompany) &&
                totalCost.equals(that.totalCost) &&
                bookRentStatus == that.bookRentStatus &&
                employee.equals(that.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, publishingCompany, copyNumber, totalCost, bookRentStatus, employee);
    }
}
