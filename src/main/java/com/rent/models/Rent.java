package com.rent.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rent")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "item_id")
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

//    @Column(name = "renter_id")
    @ManyToOne
    @JoinColumn(name = "renter_id", nullable = false)
    private User renter;

    @Column
    private Date startDate = new Date();

    @Column
    private Date endDate = new Date();

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getRenter() {
        return renter;
    }

    public void setRenter(User renter) {
        this.renter = renter;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
