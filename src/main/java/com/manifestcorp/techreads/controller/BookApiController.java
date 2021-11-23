package com.manifestcorp.techreads.controller;


import com.manifestcorp.techreads.model.Book;
import com.manifestcorp.techreads.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookApiController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping({"", "/"})
    public List<Book> findAll() {
       return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Book> findById(@PathVariable Long id) {
        return bookRepository.findById(id);
    }

    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    public Book createBook(@RequestBody Book newBook) {
        return bookRepository.saveAndFlush(newBook);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        return bookRepository.saveAndFlush(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }

}
