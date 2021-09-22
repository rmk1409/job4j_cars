package ru.job4j.cars.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.*;

import java.util.List;
import java.util.function.Function;

public class AdRepository {
    private final SessionFactory factory;
    private final String commonQuery = "" +
            "select distinct a " +
            "from Advertisement a " +
            "join fetch a.author author " +
            "join fetch a.car c " +
            "join fetch c.bodyType bt " +
            "join fetch c.brand b ";

    private AdRepository() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static AdRepository getInstance() {
        return Holder.INSTANCE;
    }

    public List<Advertisement> getTodayAdvertisements() {
        return tx(session -> {
            String hql = commonQuery +
                    "where date(a.created) = current_date";
            return session.createQuery(hql).list();
        });
    }

    public List<Advertisement> getAdvertisementsWithPhoto() {
        return tx(session -> {
            String hql = commonQuery +
                    "where c.photo is not null";
            return session.createQuery(hql)
                    .list();
        });
    }

    public List<Advertisement> getAdvertisementsByBrand(Brand brand) {
        return tx(session -> {
            String hql = commonQuery +
                    "where c.brand = :brand";
            return session.createQuery(hql)
                    .setParameter("brand", brand)
                    .list();
        });
    }

    public List<Advertisement> getAllAdvertisements() {
        return tx(session -> session.createQuery(commonQuery).list());
    }

    public void changeSold(int id) {
        tx(session -> {
            Advertisement ad = session.load(Advertisement.class, id);
            ad.setSold(!ad.getIsSold());
            return session.save(ad);
        });
    }

    public Author findUserByName(String name) {
        return tx(session ->
                session.createQuery("from Author where name = :name", Author.class)
                        .setParameter("name", name)
                        .uniqueResult()
        );
    }

    public void save(Author author) {
        tx(session -> session.save(author));
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public List<Brand> getAllBrands() {
        return tx(session -> session.createQuery("from Brand ").list());
    }

    public List<BodyType> getAllBodyTypes() {
        return tx(session -> session.createQuery("from BodyType ").list());
    }

    public void saveAdvertisement(String description, int brandId, int bodyTypeId, int authorId) {
        tx(session -> {
            Brand brand = session.load(Brand.class, brandId);
            BodyType bodyType = session.load(BodyType.class, bodyTypeId);
            Author author = session.load(Author.class, authorId);
            Car car = Car.of(description, bodyType, brand);
            session.save(car);
            Advertisement advertisement = Advertisement.of(false, car, author);
            return session.save(advertisement);
        });
    }

    private static class Holder {
        private static final AdRepository INSTANCE = new AdRepository();
    }
}
