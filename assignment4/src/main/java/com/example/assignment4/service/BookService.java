package com.example.assignment4.service;

import com.example.assignment4.dao.BookDao;
import com.example.assignment4.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    @Transactional
    public Book addNewBook(Book book){
        return bookDao.addBook(book);
    }

    @Transactional
    public Book getBookById(Integer id){
        return bookDao.findBookById(id);
    }
}
