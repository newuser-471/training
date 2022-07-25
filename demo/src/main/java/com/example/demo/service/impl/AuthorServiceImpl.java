package com.example.demo.service.impl;

import com.example.demo.Dto.AuthorDto;
import com.example.demo.Dto.CommonResponse;
import com.example.demo.entity.Author;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public CommonResponse getAuthorById(Integer authorId) {
        return new CommonResponse(authorRepository.findById(authorId).get());
    }

    @Override
    public CommonResponse getAllAuthors() {
        Iterable<Author> res = authorRepository.findAll();
        if(res==null){
            throw new ResourceNotFoundException("data not found");
        }
        List<Author> list = new ArrayList<>();
        res.forEach(list::add);
        return new CommonResponse(list.stream().map(a->new AuthorDto(a)).collect(Collectors.toList()));
    }

    @Override
    public CommonResponse addNewAuthor(Author author) {
        return new CommonResponse(new AuthorDto(authorRepository.save(author)));
    }

    @Override
    public CommonResponse deleteAuthorById(Integer id) {
        authorRepository.deleteById(id);
        return new CommonResponse("delete successful");
    }
}
