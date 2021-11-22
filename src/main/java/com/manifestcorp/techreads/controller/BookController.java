package com.manifestcorp.techreads.controller;

import com.manifestcorp.techreads.model.Book;
import com.manifestcorp.techreads.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping({"","/"})
    public ModelAndView books() {
        ModelAndView mav = new ModelAndView("books");
        List<Book> books = bookRepository.findAll();
        mav.addObject("books", books);
        return mav;
    }

    @RequestMapping("/{bookID}")
    public ModelAndView bookDetail(@PathVariable(value="bookID") String id) {
        Long bookId = Long.valueOf(id);
        ModelAndView mav = new ModelAndView("bookDetail");
        Book book = bookRepository.getById(bookId);
        mav.addObject("book", book);
        return mav;
    }

    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("bookForm", new Book());
        model.addAttribute("addOrEdit", "Add");
        return "add";
    }

    @RequestMapping(value={"","/"}, method = POST)
    public RedirectView addBook(Book book, BindingResult bindingResult) {

        Book newBook = bookRepository.saveAndFlush(book);
        return new RedirectView("/books/" + newBook.getId());
    }

    @RequestMapping("/edit/{bookID}")
    public String edit(Model model, @PathVariable(value="bookID") String id) {
        Long bookId = Long.valueOf(id);
        Book book = bookRepository.getById(bookId);
        model.addAttribute("bookForm", book);
        model.addAttribute("addOrEdit", "Edit");
        return "add";
    }

    @RequestMapping("/delete/{bookID}")
    public RedirectView delete(@PathVariable(value="bookID") String id) {
        Long bookId = Long.valueOf(id);
        bookRepository.deleteById(bookId);
        return new RedirectView("/books", true);
    }
}
