package com.example.assignment4.dto;

import com.example.assignment4.entity.Author;

public class AuthorDto {

    private String name;

    public AuthorDto(Author author){
        this.name = author.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
