package com.example.assignment4.controller;

import com.example.assignment4.dto.ResDto;
import com.example.assignment4.entity.Author;
import com.example.assignment4.entity.AuthorBook;
import com.example.assignment4.entity.Book;
import com.example.assignment4.service.AuthorService;
import com.example.assignment4.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AllController {
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @PostMapping("/addAuthor")
    public ResponseEntity<Author> addOneAuthor(@RequestBody Author author){
        return new ResponseEntity<>(authorService.addNewAuthor(author), HttpStatus.OK);
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addOneBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.addNewBook(book), HttpStatus.OK);
    }

    @GetMapping("/getRes")
    public ResponseEntity<List<ResDto>> getOneAuthor(@RequestParam(name = "id")Integer authorId){
        return new ResponseEntity<>(authorService.getAuthorWithBook(authorId),HttpStatus.OK);
    }

    @GetMapping("/getBook")
    public ResponseEntity<Book> getOneBook(@RequestParam(name = "id")Integer bookId){
        return new ResponseEntity<>(bookService.getBookById(bookId),HttpStatus.OK);
    }

    @PostMapping("/addAll")
    public ResponseEntity<AuthorBook> addPair(@RequestParam(name = "a_id") Integer authorId, @RequestParam(name = "b_id") Integer bookId){
        authorService.addAuthorBook(authorId,bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
