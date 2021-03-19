package edu.ukma.javaee.lab7onwards.services;

import edu.ukma.javaee.lab7onwards.models.book.BookFilter;
import edu.ukma.javaee.lab7onwards.models.book.BookRequest;
import edu.ukma.javaee.lab7onwards.models.book.BookResponse;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IBookService {
    void addBook(BookRequest bookRequest);

    BookResponse getBook(String isbn);

    List<BookResponse> getBooks(PageRequest pr, BookFilter bookFilter);
}
