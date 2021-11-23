package com.manifestcorp.techreads;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.manifestcorp.techreads.model.Book;
import com.manifestcorp.techreads.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class BookApiControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BookRepository mockBookRepository;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void findAllReturnsJsonListOfBooks() throws Exception {
        List<Book> mockBooks =  Arrays.asList(new Book(), new Book(), new Book());
        String mockBooksJson = mapper.writeValueAsString(mockBooks);
        Mockito.when(mockBookRepository.findAll()).thenReturn(mockBooks);
        this.mockMvc.perform(get("/api/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mockBooksJson));
    }

    @Test
    public void findByIdReturnsJsonBook() throws Exception {
        Long bookId = 1L;
        Book mockBook = new Book();
        String mockBookJson = mapper.writeValueAsString(mockBook);
        Mockito.when(mockBookRepository.findById(bookId)).thenReturn(Optional.of(mockBook));
        this.mockMvc.perform(get("/api/books/" + bookId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mockBookJson));
    }

    @Test
    public void createBookInvokesSaveAndFlushAndReturnsSavedBook() throws Exception {
        Book mockBook = new Book();
        String mockBookJson = mapper.writeValueAsString(mockBook);
        Mockito.when(mockBookRepository.saveAndFlush(any(Book.class))).thenReturn(mockBook);
        this.mockMvc.perform(post("/api/books/").contentType(MediaType.APPLICATION_JSON).content(mockBookJson))
                .andExpect(content().json(mockBookJson));
        Mockito.verify(mockBookRepository).saveAndFlush(any(Book.class));
    }

    @Test
    public void updateBookInvokesSaveAndFlushAndReturnsUpdatedBook() throws Exception {
        Book mockBook = new Book();
        mockBook.setId(1L);
        String mockBookJson = mapper.writeValueAsString(mockBook);
        Mockito.when(mockBookRepository.saveAndFlush(any(Book.class))).thenReturn(mockBook);
        this.mockMvc.perform(put("/api/books/" + mockBook.getId()).contentType(MediaType.APPLICATION_JSON).content(mockBookJson))
                .andExpect(content().json(mockBookJson));
        Mockito.verify(mockBookRepository).saveAndFlush(any(Book.class));
    }

    @Test
    public void deleteBookInvokesDeleteByIdWithIdFromQueryParam() throws Exception {
        Long bookId = 1L;
        this.mockMvc.perform(delete("/api/books/" + bookId));
        Mockito.verify(mockBookRepository).deleteById(bookId);
    }
}
