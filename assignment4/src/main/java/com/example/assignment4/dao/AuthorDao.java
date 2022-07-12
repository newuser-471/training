package com.example.assignment4.dao;

import com.example.assignment4.entity.Author;
import com.example.assignment4.entity.AuthorBook;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class AuthorDao extends AbstractHibernateDao<Author>{

    public AuthorDao(){
        setClazz(Author.class);
    }

    public Author addAuthor(Author entity) {
        return merge(entity);
    }

    public Author findAuthorWithBook(Integer authorId){
        Session session = getCurrentSession();
        Query joinfecth = session.createQuery("FROM Author a join fetch a.authorBookList l where a.id=:id");
        joinfecth.setParameter("id", authorId);
        Author res = (Author) joinfecth.getSingleResult();
        return res;
    }

    public Author findAuthorById(Integer id) {
        return findById(id);
    }
}
