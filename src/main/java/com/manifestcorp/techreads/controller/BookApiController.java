package com.manifestcorp.techreads.controller;


import com.manifestcorp.techreads.model.Book;
import com.manifestcorp.techreads.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookApiController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/")
    public List<Book> findAll() {
       return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable String id) {
        Long bookId = Long.valueOf(id);
        return bookRepository.findById(bookId).orElse(new Book());
    }

}
