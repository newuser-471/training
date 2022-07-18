package com.example.assignment4.service.impl;

import com.example.assignment4.dao.AuthorBookDao;
import com.example.assignment4.dao.AuthorDao;
import com.example.assignment4.dao.BookDao;
import com.example.assignment4.dto.AuthorDto;
import com.example.assignment4.dto.CommonResponse;
import com.example.assignment4.dto.ResDto;
import com.example.assignment4.entity.Author;
import com.example.assignment4.entity.AuthorBook;
import com.example.assignment4.entity.Book;
import com.example.assignment4.exception.ResourceNotFoundException;
import com.example.assignment4.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    Logger log = LoggerFactory.getLogger(this.getClass());

    private final AuthorBookDao authorBookDao;

    private final AuthorDao authorDao;

    private final BookDao bookDao;

    @Autowired
    public AuthorServiceImpl(AuthorBookDao authorBookDao, AuthorDao authorDao, BookDao bookDao) {
        this.authorBookDao = authorBookDao;
        this.authorDao = authorDao;
        this.bookDao = bookDao;
    }


    @Override
    @Transactional
    public CommonResponse getAuthorById(Integer authorId){
        Optional<Author> opt = Optional.ofNullable(authorDao.findAuthorById(authorId));
        if(!opt.isPresent()){
            log.warn("failed to find data with given id");
            throw new ResourceNotFoundException("no data found with given id");
        }
        AuthorDto res = new AuthorDto(opt.get());
        return new CommonResponse(res);
    }

    @Override
    @Transactional
    public CommonResponse getAllAuthors() {
        Optional<List<Author>> opt = Optional.ofNullable(authorDao.findAll());
        if(!opt.isPresent()){
            log.warn("failed to find data");
            throw new ResourceNotFoundException("no data found");
        }
        List<AuthorDto> res = opt.get().stream().map(a->new AuthorDto(a)).collect(Collectors.toList());
        return new CommonResponse(res);
    }

    @Override
    @Transactional
    public CommonResponse getAuthorWithBook(Integer authorId){
        Optional<List<ResDto>> opt = Optional.ofNullable(authorDao.findAuthorWithBook(authorId));
        if(!opt.isPresent()){
            log.warn("failed to find data with given id");
            throw new ResourceNotFoundException("not data found with given id");
        }

        return new CommonResponse(opt.get());
    }

    @Override
    @Transactional
    public void addAuthorBook(Integer authorId, Integer bookId){
        Author author = authorDao.findById(authorId);
        Book book = bookDao.findById(bookId);
        AuthorBook authorBook = new AuthorBook();
        authorBook.setAuthor(author);
        authorBook.setBook(book);
        authorBookDao.addPair(authorBook);
    }

    @Override
    @Transactional
    public CommonResponse addNewAuthor(Author author){
        Optional<Author> opt = Optional.ofNullable(authorDao.addAuthor(author));
        if(!opt.isPresent()){
            log.warn("failed to add given data");
            throw new ResourceNotFoundException("not able to add given data");
        }
        AuthorDto res = new AuthorDto(opt.get());
        return new CommonResponse(res);
    }

    @Override
    @Transactional
    public CommonResponse deleteAuthorById(Integer id) {
        Optional<Author> opt = Optional.ofNullable(authorDao.findAuthorById(id));
        if(!opt.isPresent()){
            log.warn("cannot find data with given id");
            throw new ResourceNotFoundException("cannot find data with given id");
        }
        authorDao.deleteById(id);
        return new CommonResponse("delete successful");
    }

    @Override
    @Transactional
    public CommonResponse getAuthorBookById(Integer id) {
        return new CommonResponse(authorBookDao.findPairById(id));
    }
}
