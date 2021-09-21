package ru.job4j.cars.model;

import javax.persistence.*;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String description;
    private String photo;
    @ManyToOne()
    @JoinColumn(nullable = false)
    private BodyType bodyType;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Brand brand;

    public static Car of(String description, String photo, BodyType bodyType, Brand brand) {
        Car car = new Car();
        car.description = description;
        car.photo = photo;
        car.bodyType = bodyType;
        car.brand = brand;
        return car;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", photo='" + photo + '\'' +
                ", bodyType=" + bodyType +
                ", brand=" + brand +
                '}';
    }
}
