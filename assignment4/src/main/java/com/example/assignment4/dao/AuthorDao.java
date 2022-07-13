package com.example.assignment4.dao;

import com.example.assignment4.dto.ResDto;
import com.example.assignment4.entity.Author;
import com.example.assignment4.entity.AuthorBook;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class AuthorDao extends AbstractHibernateDao<Author>{

    public AuthorDao(){
        setClazz(Author.class);
    }

    public Author addAuthor(Author entity) {
        return merge(entity);
    }

    public List<ResDto> findAuthorWithBook(Integer authorId){
        Session session = getCurrentSession();
        Query join = session.createQuery(
                "select b.name as book_name, a.name as author_name\n" +
                        "from Book b join AuthorBook ab on b.id = ab.book.id join Author a on ab.author.id = a.id\n" +
                        "where a.id = :id");
        join.setParameter("id", authorId);
        List<ResDto> res = join.getResultList();
        return res;
    }

    public Author findAuthorById(Integer id) {
        return findById(id);
    }
}
