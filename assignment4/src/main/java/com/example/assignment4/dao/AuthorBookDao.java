package com.example.assignment4.dao;

import com.example.assignment4.entity.AuthorBook;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class AuthorBookDao extends AbstractHibernateDao<AuthorBook> {

    public AuthorBookDao(){
        setClazz(AuthorBook.class);
    }

    public void addPair(AuthorBook entity){
        Session session = getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.persist(entity);
        tx.commit();
    }

    public AuthorBook findPairById(Integer id) {
        return super.findById(id);
    }

    public AuthorBook updatePair(AuthorBook entity) {
        return merge(entity);
    }

    @Override
    public void deleteById(Integer entityId) {
        super.deleteById(entityId);
    }

}
