package com.company.dao.impl;

import at.favre.lib.crypto.bcrypt.*;
import com.company.dao.inter.*;
import com.company.entity.*;
import org.springframework.stereotype.*;

import javax.persistence.PersistenceContext;

import javax.persistence.*;
import java.util.*;

@Repository("userDao1")
public class UserDaoImpl implements UserDaoInter {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {

        String jpql = "select u from User u where 1=1";

        if (name != null && !name.trim().isEmpty()) {
            jpql += " and u.name = :name";
        }

        if (surname != null && !surname.trim().isEmpty()) {
            jpql += " and u.surname = :surname";
        }

        if (nationalityId != null) {
            jpql += " and u.nationality.id = :nid";
        }

        Query query = entityManager.createQuery(jpql, User.class);

        if (name != null && !name.trim().isEmpty()) {
            query.setParameter("name", name);
        }

        if (surname != null && !surname.trim().isEmpty()) {
            query.setParameter("surname", surname);
        }

        if (nationalityId != null) {
            query.setParameter("nid", nationalityId);
        }

        return query.getResultList();
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        Query query = entityManager.createQuery("select u from User u where u.email= :email and u.password= :password", User.class);

        query.setParameter("email", email);
        query.setParameter("password", password);

        List<User> list = query.getResultList();

        if (list.size() == 1) {
            return list.get(0);
        }

        return null;
    }
    
    @Override
    public User findByEmail(String email) {
        Query query = entityManager.createQuery("select u from User u where u.email= :email", User.class);
        query.setParameter("email", email);

        List<User> list = query.getResultList();

        if (list.size() == 1) {
            return list.get(0);
        }

        return null;
    }

    @Override
    public boolean updateUser(User u) {
        entityManager.merge(u);

        return true;
    }

    @Override
    public boolean removeUser(int id) {
        User u = entityManager.find(User.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(u);
        entityManager.getTransaction().commit();

        return true;
    }

    @Override
    public User getById(int userId) {
        User u = entityManager.find(User.class, userId);

        return u;
    }

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean addUser(User u) {
        u.setPassword(crypt.hashToString(4, u.getPassword().toCharArray()));

        entityManager.getTransaction().begin();
        entityManager.persist(u);
        entityManager.getTransaction().commit();

        return true;
    }

//    public static void main(String[] args) {
//        UserDaoInter userDao = Context.instanceUserDao();
//        
//        User u = userDao.getById(3);
//        char[] c = {'1', '2', '3', '4', '5', 's'};
//        u.setPassword(crypt.hashToString(4, c));
//        System.out.println(u.getPassword());
//    }
}
