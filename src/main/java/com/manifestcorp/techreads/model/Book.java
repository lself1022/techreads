package com.manifestcorp.techreads.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String title;

    public Book() {}
    public Book(String title) { this.title = title; }

    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }

    public void setTitle(String title) { this.title = title; }
    public String getTitle() { return title; }
}
