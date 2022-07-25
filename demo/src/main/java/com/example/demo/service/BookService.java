package com.example.demo.service;

import com.example.demo.Dto.CommonResponse;
import com.example.demo.entity.Book;

public interface BookService {

    CommonResponse createBook(Book book);

    CommonResponse findBookById(Integer id);

    CommonResponse findAllBook();

    CommonResponse deleteBookById(Integer id);
}
