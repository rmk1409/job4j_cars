package ru.job4j.cars.model;

import javax.persistence.*;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String photo;
    @ManyToOne()
    @JoinColumn(nullable = false)
    private BodyType bodyType;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Brand brand;
}
