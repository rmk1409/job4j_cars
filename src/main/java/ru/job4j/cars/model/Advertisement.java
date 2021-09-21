package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "is_sold")
    private boolean isSold;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @OneToOne
    private Car car;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Author author;

    public static Advertisement of(boolean isSold, Car car, Author author) {
        Advertisement advertisement = new Advertisement();
        advertisement.isSold = isSold;
        advertisement.created = new Date();
        advertisement.car = car;
        advertisement.author = author;
        return advertisement;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", isSold=" + isSold +
                ", created=" + created +
                ", car=" + car +
                ", author=" + author +
                '}';
    }
}
