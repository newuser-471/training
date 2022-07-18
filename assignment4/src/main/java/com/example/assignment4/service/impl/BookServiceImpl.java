package com.example.assignment4.service.impl;

import com.example.assignment4.dao.BookDao;
import com.example.assignment4.dto.BookDto;
import com.example.assignment4.dto.CommonResponse;
import com.example.assignment4.entity.Book;
import com.example.assignment4.exception.ResourceNotFoundException;
import com.example.assignment4.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    Logger log = LoggerFactory.getLogger(this.getClass());
    private final BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao){
        this.bookDao = bookDao;
    }

    @Override
    @Transactional
    public CommonResponse addNewBook(Book book){
        Optional<Book> opt = Optional.ofNullable(bookDao.addBook(book));
        if(!opt.isPresent()){
            throw new ResourceNotFoundException("cannot add give data");
        }
        BookDto res = new BookDto(opt.get());
        return new CommonResponse(res);
    }

    @Override
    @Transactional
    public CommonResponse getAllBooks() {
        Optional<List<Book>> opt = Optional.ofNullable(bookDao.findAll());
        if(!opt.isPresent()){
            throw new ResourceNotFoundException("cannot add give data");
        }
        List<BookDto> res = opt.get().stream().map(s->new BookDto(s)).collect(Collectors.toList());
        return new CommonResponse(res);
    }


    @Override
    @Transactional
    public CommonResponse getBookById(Integer id){
        Optional<Book> opt = Optional.ofNullable(bookDao.findBookById(id));
        if(!opt.isPresent()){
            log.warn("cannot find data with given id");
            throw new ResourceNotFoundException("cannot find data with given id");
        }
        BookDto res = new BookDto(opt.get());
        return new CommonResponse(res);
    }

    @Override
    @Transactional
    public CommonResponse deleteBookById(Integer id) {
        Optional<Book> opt = Optional.ofNullable(bookDao.findBookById(id));
        if(!opt.isPresent()){
            log.warn("cannot find data with given id");
            throw new ResourceNotFoundException("cannot find data with given id");
        }
        bookDao.deleteById(id);
        return new CommonResponse("delete successful");
    }
}
