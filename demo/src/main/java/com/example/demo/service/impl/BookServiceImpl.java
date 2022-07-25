package com.example.demo.service.impl;

import com.example.demo.Dto.BookDto;
import com.example.demo.Dto.CommonResponse;
import com.example.demo.entity.Book;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public CommonResponse createBook(Book book) {
        return new CommonResponse(new BookDto(bookRepository.save(book)));
    }

    @Override
    public CommonResponse findBookById(Integer id) {
        if(!bookRepository.existsById(id)){
            throw new ResourceNotFoundException("no data found with id:"+id);
        }
        return new CommonResponse(bookRepository.findById(id).get());
    }

    @Override
    public CommonResponse findAllBook() {
        Iterable<Book> res = bookRepository.findAll();
        if(res == null){
            throw new ResourceNotFoundException("no result found");
        }
        List<Book> list = new ArrayList<>();
        res.forEach(list::add);
        return new CommonResponse(list.stream().map(b->new BookDto(b)).collect(Collectors.toList()));
    }

    @Override
    public CommonResponse deleteBookById(Integer id) {
        bookRepository.deleteById(id);
        return new CommonResponse("delete successful");
    }
}
