package com.example.demo.controller;

import com.example.demo.Dto.CommonResponse;
import com.example.demo.entity.Author;
import com.example.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public ResponseEntity<CommonResponse> getAllAuthor(){
        return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getAuthorById(@PathVariable Integer id){
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CommonResponse> addAuthor(@RequestBody Author author){
        return new ResponseEntity<>(authorService.addNewAuthor(author), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> deleteAuthor(@PathVariable Integer id){
        return new ResponseEntity<>(authorService.deleteAuthorById(id), HttpStatus.OK);
    }
}
