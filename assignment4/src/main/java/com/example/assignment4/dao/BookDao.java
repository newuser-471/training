package com.example.assignment4.dao;

import com.example.assignment4.entity.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao extends AbstractHibernateDao<Book> {

    public BookDao(){
        setClazz(Book.class);
    }

    public Book addBook(Book entity) {
        return merge(entity);
    }

    public Book findBookById(Integer id) {
        return findById(id);
    }

    public Book updateBook(Book entity) {
        return merge(entity);
    }

    @Override
    public void deleteById(Integer id) {
        super.deleteById(id);
    }
}
