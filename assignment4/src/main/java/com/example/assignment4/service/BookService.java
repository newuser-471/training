package com.example.assignment4.service;

import com.example.assignment4.dto.CommonResponse;
import com.example.assignment4.entity.Book;

public interface BookService {

    CommonResponse addNewBook(Book book);

    CommonResponse getAllBooks();

    CommonResponse getBookById(Integer id);

    CommonResponse deleteBookById(Integer id);
}
