package edu.ukma.javaee.lab7onwards.controllers;

import edu.ukma.javaee.lab7onwards.models.book.BookFilter;
import edu.ukma.javaee.lab7onwards.models.book.BookRequest;
import edu.ukma.javaee.lab7onwards.services.IBookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@AllArgsConstructor
public class Book {
    private final IBookService bookService;

    @PreAuthorize("hasAuthority(ADD_BOOKS)")
    @PostMapping("/books")
    public void addBook(@RequestBody BookRequest newBook) {
        bookService.addBook(newBook);
    }

    @GetMapping("/books/{isbn}")
    public String getBook(Model model, @PathVariable String isbn) {
        model.addAttribute("books", Collections.singletonList(bookService.getBook(isbn)));
        return "/index";
    }

    @GetMapping("/books")
    public String getBooks(Model model, @RequestParam int page, @RequestBody(required = false) BookFilter filter) {
        model.addAttribute("books", bookService.getBooks(PageRequest.of(page, 10), filter));
        return "/index";
    }
}
