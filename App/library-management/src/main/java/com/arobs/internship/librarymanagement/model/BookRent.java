package com.arobs.internship.librarymanagement.model;

import com.arobs.internship.librarymanagement.model.enums.BookRentStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "book_rent")
public class BookRent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_rent_id")
    private int id;

    @Column(nullable = false, name = "rental_date")
    private LocalDateTime rentalDate;

    @Column(nullable = false, name = " return_date")
    private LocalDateTime returnDate;

    @Column(nullable = false, length = 50, name = "Status")
    @Enumerated(EnumType.STRING)
    private BookRentStatus bookRentStatus;

    @Column(nullable = false, name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "copy_id")
    private Copy copy;

    public BookRent() {
    }

    public BookRent(LocalDateTime rentalDate, LocalDateTime returnDate, BookRentStatus bookRentStatus, String note, Book book, Employee employee, Copy copy) {
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.bookRentStatus = bookRentStatus;
        this.note = note;
        this.book = book;
        this.employee = employee;
        this.copy = copy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDateTime rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public BookRentStatus getBookRentStatus() {
        return bookRentStatus;
    }

    public void setBookRentStatus(BookRentStatus bookRentStatus) {
        this.bookRentStatus = bookRentStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Copy getCopy() {
        return copy;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookRent bookRent = (BookRent) o;
        return id == bookRent.id &&
                rentalDate.equals(bookRent.rentalDate) &&
                returnDate.equals(bookRent.returnDate) &&
                bookRentStatus == bookRent.bookRentStatus &&
                note.equals(bookRent.note) &&
                book.equals(bookRent.book) &&
                employee.equals(bookRent.employee) &&
                copy.equals(bookRent.copy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rentalDate, returnDate, bookRentStatus, note, book, employee, copy);
    }
}
