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
}
