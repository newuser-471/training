package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "author_book")
public class AuthorBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "a_id")
    private Author author;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b_id")
    private Book book;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuthorBook() {
    }

    public AuthorBook(Integer id, Author author, Book book) {
        this.id = id;
        this.author = author;
        this.book = book;
    }
}
