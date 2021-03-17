package edu.ukma.javaee.lab7onwards.controllers;

import edu.ukma.javaee.lab7onwards.models.BookFilter;
import edu.ukma.javaee.lab7onwards.models.BookRequest;
import edu.ukma.javaee.lab7onwards.models.BookResponse;
import edu.ukma.javaee.lab7onwards.services.IBookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class Book {
    private final IBookService bookService;

    @PostMapping("/books")
    public void addBook(@RequestBody BookRequest newBook) {
        bookService.addBook(newBook);
    }

    @GetMapping("/books/{isbn}")
    public BookResponse getBook(@PathVariable String isbn) {
        return bookService.getBook(isbn);
    }

    @GetMapping("/books")
    public List<BookResponse> getBooks(@RequestParam int page, @RequestBody(required = false) BookFilter filter) {
        return bookService.getBooks(PageRequest.of(page, 10), filter);
    }

    @DeleteMapping("/books/{isbn}")
    public void removeBook(@PathVariable String isbn) {
        throw new UnsupportedOperationException("Not yet required, would involve deleting books from all favorite lists");
    }
}
