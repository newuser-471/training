package com.example.assignment4.service;

import com.example.assignment4.dto.CommonResponse;
import com.example.assignment4.dto.ResDto;
import com.example.assignment4.entity.Author;

import java.util.List;

public interface AuthorService {

    CommonResponse getAuthorById(Integer authorId);

    CommonResponse getAllAuthors();

    CommonResponse getAuthorWithBook(Integer authorId);

    void addAuthorBook(Integer authorId, Integer bookId);

    CommonResponse addNewAuthor(Author author);

    CommonResponse deleteAuthorById(Integer id);

    CommonResponse getAuthorBookById(Integer id);

}
