package ru.job4j.cars.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.Advertisement;
import ru.job4j.cars.model.Brand;

import java.util.Calendar;
import java.util.Date;
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
                    "where a.created >= :date";
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR, -24);
            Date date = calendar.getTime();
            return session.createQuery(hql)
                    .setParameter("date", date)
                    .list();
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

    private static class Holder {
        private static final AdRepository INSTANCE = new AdRepository();
    }
}
