package com.example.demo.Dto;

import com.example.demo.entity.Author;

public class AuthorDto {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuthorDto(Author author){
        this.name = author.getName();
    }
}
