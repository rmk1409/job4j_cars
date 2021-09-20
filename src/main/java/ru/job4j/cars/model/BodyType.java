package ru.job4j.cars.model;

import javax.persistence.*;

@Entity
@Table(name = "body_type")
public class BodyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
}
