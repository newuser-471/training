package com.example.demo.Dto;

import com.example.demo.entity.Book;

public class BookDto {

    public BookDto(Book book) {
        this.name = book.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
