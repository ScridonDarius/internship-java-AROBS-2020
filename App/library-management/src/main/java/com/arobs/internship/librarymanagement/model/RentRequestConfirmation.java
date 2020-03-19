package com.arobs.internship.librarymanagement.model;

import com.arobs.internship.librarymanagement.model.enums.RentRequestConfirmationStatus;

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

    @Column(nullable = false, length = 50, name = "Status")
    @Enumerated(EnumType.STRING)
    private RentRequestConfirmationStatus rentRequestConfirmationStatus;

    public RentRequestConfirmation() {
    }

    public RentRequestConfirmation(RentRequest rentRequestId, Copy copyId, LocalDateTime confirmation, RentRequestConfirmationStatus rentRequestConfirmationStatus) {
        this.rentRequestId = rentRequestId;
        this.copyId = copyId;
        this.confirmation = confirmation;
        this.rentRequestConfirmationStatus = rentRequestConfirmationStatus;
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

    public RentRequestConfirmationStatus getRentRequestConfirmationStatus() {
        return rentRequestConfirmationStatus;
    }

    public void setRentRequestConfirmationStatus(RentRequestConfirmationStatus rentRequestConfirmationStatus) {
        this.rentRequestConfirmationStatus = rentRequestConfirmationStatus;
    }
}