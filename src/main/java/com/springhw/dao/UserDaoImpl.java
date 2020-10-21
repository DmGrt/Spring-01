package com.springhw.dao;

import com.springhw.exceptions.DataProcessingException;
import com.springhw.models.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert user: " + user, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public User getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE id = :id");
            query.setParameter("id", id);
            return query.getSingleResult();
        }
    }

    @Override
    public List<User> listUsers() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from "
                    + User.class.getSimpleName(), User.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving all users", e);
        }
    }
}
