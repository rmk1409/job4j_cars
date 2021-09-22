package ru.job4j.cars.model;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String description;
    @ManyToOne()
    @JoinColumn(nullable = false)
    private BodyType bodyType;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Brand brand;

    public static Car of(String description, BodyType bodyType, Brand brand) {
        Car car = new Car();
        car.description = description;
        car.bodyType = bodyType;
        car.brand = brand;
        return car;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public Brand getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", bodyType=" + bodyType +
                ", brand=" + brand +
                '}';
    }
}
