package com.arobs.internship.librarymanagement.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rent_request_confirmation")
public class RentRequestConfirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "confirmation_id")
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rent_request_id")
    private RentRequest rentRequestId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "copy_id")
    private Copy copyId;

    @Column(nullable = false, name = "confirmation_date")
    private LocalDateTime confirmation;


    public RentRequestConfirmation() {
    }

    public RentRequestConfirmation(RentRequest rentRequestId, Copy copyId, LocalDateTime confirmation) {
        this.rentRequestId = rentRequestId;
        this.copyId = copyId;
        this.confirmation = confirmation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RentRequest getRentRequestId() {
        return rentRequestId;
    }

    public void setRentRequestId(RentRequest rentRequestId) {
        this.rentRequestId = rentRequestId;
    }

    public Copy getCopyId() {
        return copyId;
    }

    public void setCopyId(Copy copyId) {
        this.copyId = copyId;
    }

    public LocalDateTime getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(LocalDateTime confirmation) {
        this.confirmation = confirmation;
    }
}