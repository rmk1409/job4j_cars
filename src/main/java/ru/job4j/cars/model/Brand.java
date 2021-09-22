package ru.job4j.cars.model;

@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;

    public static Brand of(int id) {
        Brand brand = new Brand();
        brand.id = id;
        return brand;
    }

    public static Brand of(String name) {
        Brand brand = new Brand();
        brand.name = name;
        return brand;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
