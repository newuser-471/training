package com.example.assignment4.controller;

import com.example.assignment4.dto.CommonResponse;
import com.example.assignment4.entity.Author;
import com.example.assignment4.entity.AuthorBook;
import com.example.assignment4.service.AuthorService;
import com.example.assignment4.service.impl.AuthorServiceImpl;
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

    @PostMapping("/add")
    public ResponseEntity<CommonResponse> addOneAuthor(@RequestBody Author author){
        return new ResponseEntity<>(authorService.addNewAuthor(author), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getAuthorById(@PathVariable Integer id){
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<CommonResponse> getAllAuthor(){
        return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
    }

    @GetMapping("/getRes/{id}")
    public ResponseEntity<CommonResponse> getOneAuthor(@PathVariable Integer id){
        return new ResponseEntity<>(authorService.getAuthorWithBook(id),HttpStatus.OK);
    }

    @PostMapping("/addAll")
    public ResponseEntity<AuthorBook> addPair(@RequestParam(name = "a_id") Integer authorId, @RequestParam(name = "b_id") Integer bookId){
        authorService.addAuthorBook(authorId,bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CommonResponse> deleteAuthor(@PathVariable Integer id){
        return new ResponseEntity<>(authorService.deleteAuthorById(id), HttpStatus.OK);
    }

    @GetMapping("/pair/{id}")
    public ResponseEntity<CommonResponse> getPair(@PathVariable Integer id){
        return new ResponseEntity<>(authorService.getAuthorBookById(id), HttpStatus.OK);
    }
}
