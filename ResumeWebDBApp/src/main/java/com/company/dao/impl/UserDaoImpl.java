package com.company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.entity.User;
import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.UserDaoInter;

import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserDaoImpl extends AbstractDao implements UserDaoInter {

    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        EntityManager entityManager = em();

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

//    @Override
//    public User findByEmailAndPassword(String email, String password) {
//        EntityManager entityManager = em();
//
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> q = cb.createQuery(User.class);
//        Root<User> postRoot = q.from(User.class);
//        CriteriaQuery<User> q1 = q
//                .where(cb.equal(postRoot.get("email"), email), cb.equal(postRoot.get("password"), password));
//
//        Query query = entityManager.createQuery(q1);
//        List<User> list = query.getResultList();
//
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//
//        return null;
//    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        EntityManager entityManager = em();
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
        EntityManager entityManager = em();

        Query query = entityManager.createQuery("select u from User u where u.email= :email", User.class);
        query.setParameter("email", email);

        List<User> list = query.getResultList();

        if (list.size() == 1) {
            return list.get(0);
        }

        return null;
    }
    
    // CriteriaBuilder
//    @Override
//    public User findByEmail(String email) {
//        EntityManager entityManager = em();
//
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> q = cb.createQuery(User.class);
//        Root<User> postRoot = q.from(User.class);
//        CriteriaQuery<User> q1 = q
//                .where(cb.equal(postRoot.get("email"), email));
//
//        Query query = entityManager.createQuery(q1);
//        List<User> list = query.getResultList();
//
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//
//        return null;
//    }
    
    // NamedQuery
//    @Override
//    public User findByEmail(String email) {
//        EntityManager entityManager = em();
//
//        Query query = entityManager.createNamedQuery("User.findByEmail", User.class);
//        
//        query.setParameter("email", email);
//        List<User> list = query.getResultList();
//
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//
//        return null;
//    }
    
    // SQL
//    @Override
//    public User findByEmail(String email) {
//        EntityManager entityManager = em();
//
//        Query query = entityManager.createNativeQuery("select * from user where email = ?", User.class);
//        
//        query.setParameter(1, email);
//        List<User> list = query.getResultList();
//
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//
//        return null;
//    }

    @Override
    public boolean updateUser(User u) {
        EntityManager entityManager = em();

        entityManager.getTransaction().begin();
        entityManager.merge(u);
        entityManager.getTransaction().commit();

        entityManager.close();
        return true;
    }

    @Override
    public boolean removeUser(int id) {
        EntityManager entityManager = em();

        User u = entityManager.find(User.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(u);
        entityManager.getTransaction().commit();

        entityManager.close();
        return true;
    }

    @Override
    public User getById(int userId) {
        EntityManager entityManager = em();;

        User u = entityManager.find(User.class, userId);

        entityManager.close();
        return u;
    }

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean addUser(User u) {
        EntityManager entityManager = em();

        u.setPassword(crypt.hashToString(4, u.getPassword().toCharArray()));

        entityManager.getTransaction().begin();
        entityManager.persist(u);
        entityManager.getTransaction().commit();

        entityManager.close();
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
