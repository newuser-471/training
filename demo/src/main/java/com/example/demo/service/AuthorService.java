package com.example.demo.service;

import com.example.demo.Dto.CommonResponse;
import com.example.demo.entity.Author;

public interface AuthorService {

    CommonResponse getAuthorById(Integer authorId);

    CommonResponse getAllAuthors();

    CommonResponse addNewAuthor(Author author);

    CommonResponse deleteAuthorById(Integer id);
}
