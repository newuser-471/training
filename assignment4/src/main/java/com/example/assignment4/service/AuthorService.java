package com.example.assignment4.service;

import com.example.assignment4.dao.AuthorBookDao;
import com.example.assignment4.dao.AuthorDao;
import com.example.assignment4.dao.BookDao;
import com.example.assignment4.dto.ResDto;
import com.example.assignment4.entity.Author;
import com.example.assignment4.entity.AuthorBook;
import com.example.assignment4.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorBookDao authorBookDao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private BookDao bookDao;

    @Transactional
    public Author getAuthorById(Integer authorId){
        return authorDao.findAuthorById(authorId);
    }

    @Transactional
    public List<ResDto> getAuthorWithBook(Integer authorId){
        return authorDao.findAuthorWithBook(authorId);
    }

    @Transactional
    public void addAuthorBook(Integer authorId, Integer bookId){
        Author author = authorDao.findById(authorId);
        Book book = bookDao.findById(bookId);
        AuthorBook authorBook = new AuthorBook();
        authorBook.setAuthor(author);
        authorBook.setBook(book);
        authorBookDao.addPair(authorBook);
    }

    @Transactional
    public Author addNewAuthor(Author author){
        return authorDao.addAuthor(author);
    }
}
