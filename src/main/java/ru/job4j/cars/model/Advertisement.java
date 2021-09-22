package ru.job4j.cars.model;

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

    public int getId() {
        return id;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public boolean getIsSold() {
        return isSold;
    }

    public Date getCreated() {
        return created;
    }

    public Car getCar() {
        return car;
    }

    public Author getAuthor() {
        return author;
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
