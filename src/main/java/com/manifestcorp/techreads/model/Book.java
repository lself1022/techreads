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
    String coverURL;
    String author;
    Double rating;

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }



    public Book() {}
    public Book(String title, String coverURL, String author, Double rating) {
        this.title = title;
        this.coverURL = coverURL;
        this.author = author;
        this.rating = rating;
    }

    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }

    public void setTitle(String title) { this.title = title; }
    public String getTitle() { return title; }
}
