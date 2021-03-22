package edu.ukma.javaee.lab7onwards.controllers;

import edu.ukma.javaee.lab7onwards.models.book.BookFilter;
import edu.ukma.javaee.lab7onwards.models.book.BookRequest;
import edu.ukma.javaee.lab7onwards.models.book.BookResponse;
import edu.ukma.javaee.lab7onwards.services.IBookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class Book {
    private final IBookService bookService;

    @PreAuthorize("hasAuthority(ADD_BOOKS)")
    @PostMapping
    public void addBook(@ModelAttribute BookRequest newBook) {
        bookService.addBook(newBook);
    }

    @GetMapping
    public List<BookResponse> getBooks(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(required = false) String isbn,
                                       @RequestParam(required = false) String titlePrefix,
                                       Principal principal) {
        if (isbn != null) {
            List<BookResponse> response = new ArrayList<>();
            BookResponse rb = bookService.getBook(isbn, principal == null ? null : principal.getName());
            if (rb != null) response.add(rb);
            return response;
        }
        BookFilter filter = new BookFilter();
        if (titlePrefix != null)
            filter.setTitlePrefix(titlePrefix);
        return bookService.getBooks(PageRequest.of(page, 10), filter, principal == null ? null : principal.getName());
    }
}
