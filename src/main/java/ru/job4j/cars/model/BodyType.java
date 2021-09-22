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

    public static BodyType of(String name) {
        BodyType bodyType = new BodyType();
        bodyType.name = name;
        return bodyType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "BodyType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
