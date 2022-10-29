package com.rent.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "rent")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "item_id", nullable = false)
    private long item;

    @JoinColumn(name = "renter_id", nullable = false)
    private long renter;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    public Rent(){}

    public Rent(Long itemId, Long renter, LocalDate date_1, LocalDate date_2) {
        this.item = itemId;
        this.renter = renter;
        this.startDate = date_1;
        this.endDate = date_2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getItem() {
        return item;
    }

    public void setItem(long item) {
        this.item = item;
    }

    public long getRenter() {
        return renter;
    }

    public void setRenter(long renter) {
        this.renter = renter;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

}
