package com.manifestcorp.techreads;

import com.manifestcorp.techreads.model.Book;
import com.manifestcorp.techreads.repository.BookRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BookRepository mockBookRepository;

    @Test
    public void bookShouldDisplayBooksViewWithListOfBooks() throws Exception {
        List<Book> mockBooks =  Arrays.asList(new Book(), new Book(), new Book());
        Mockito.when(mockBookRepository.findAll()).thenReturn(mockBooks);
        this.mockMvc.perform(get("/books"))
                .andExpect(view().name("books"))
                .andExpect(model().attribute("books",mockBooks));
    }

    @Test
    public void bookDetailShouldDisplayBookDetailViewWithBookModel() throws Exception {
        Long bookId = 1L;
        Book mockBook = new Book();
        Mockito.when(mockBookRepository.getById(bookId)).thenReturn(mockBook);
        this.mockMvc.perform(get("/books/" + bookId))
                .andExpect(view().name("bookDetail"))
                .andExpect(model().attribute("book", mockBook));
        Mockito.verify(mockBookRepository).getById(bookId);
    }

    @Test
    public void addShouldDisplayAddFormWithBookModel() throws Exception {
        this.mockMvc.perform(get("/books/add"))
                .andExpect(view().name("add"))
                .andExpect(model().attribute("addOrEdit", "Add"))
                .andExpect(model().attribute("bookForm", instanceOf(Book.class)));
    }

    @Test
    public void addBookShouldSaveBookAndRedirectToItsDetailPage() throws Exception {
        Book mockBook = new Book();
        mockBook.setId(1L);
        mockBook.setTitle("Book Title");
        mockBook.setAuthor("A. Author");
        mockBook.setCoverURL(" ");
        mockBook.setRating(1.0);
        Mockito.when(mockBookRepository.saveAndFlush(any(Book.class))).thenReturn(mockBook);

        this.mockMvc.perform( post("/books")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", mockBook.getTitle())
                        .param("author", mockBook.getAuthor())
                        .param("id", mockBook.getId().toString())
                        .param("coverURL", mockBook.getCoverURL())
                        .param("rating",mockBook.getRating().toString())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books/" + mockBook.getId()));
        Mockito.verify(mockBookRepository).saveAndFlush(any(Book.class));
    }

    @Test
    public void editBookShouldDisplayAddFormWithExistingBookModel() throws Exception {
        Long bookId = 1L;
        Book mockBook = new Book();
        Mockito.when(mockBookRepository.getById(bookId)).thenReturn(mockBook);
        this.mockMvc.perform(get("/books/edit/" + bookId))
                .andExpect(view().name("add"))
                .andExpect(model().attribute("bookForm", mockBook));
        Mockito.verify(mockBookRepository).getById(bookId);
    }

    @Test
    public void deleteShouldCallDeleteByIdAndRedirect() throws Exception {
        Long bookId = 1L;
        this.mockMvc.perform(get("/books/delete/" + bookId))
                .andExpect(redirectedUrl("/books"));
        Mockito.verify(mockBookRepository).deleteById(bookId);
    }
}
