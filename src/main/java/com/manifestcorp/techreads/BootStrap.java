package com.manifestcorp.techreads;

import com.manifestcorp.techreads.model.Book;
import com.manifestcorp.techreads.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    protected BookRepository bookRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(bookRepository.count() < 3) {
            bookRepository.save(new Book(
                    "Beginning Groovy and Grails",
                    "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1347345502i/3417053.jpg",
                    "Jim Shingler, Joseph Faisal Naisarat, Chris Judd",
                    3.57));
            bookRepository.save(new Book("Pro Eclipse JST",
                    "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1348269990i/709484.jpg",
                    "Chris Judd",
                    4.00));
            bookRepository.save(new Book("Enterprise Java Development on a Budget",
                    "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1347504083i/2092393.jpg",
                    "Brian Sam-Bodden, Chris Judd",
                    5.00));
        }
    }
}
