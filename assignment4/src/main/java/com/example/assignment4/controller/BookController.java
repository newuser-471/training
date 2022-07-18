package com.example.assignment4.controller;


import com.example.assignment4.dto.CommonResponse;
import com.example.assignment4.entity.Book;
import com.example.assignment4.service.BookService;
import com.example.assignment4.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping("/addBook")
    public ResponseEntity<CommonResponse> addOneBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.addNewBook(book), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<CommonResponse> findAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getOneBook(@PathVariable Integer id){
        return new ResponseEntity<>(bookService.getBookById(id),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CommonResponse> deleteBook(@PathVariable Integer id){
        return new ResponseEntity<>(bookService.deleteBookById(id), HttpStatus.OK);
    }
}
