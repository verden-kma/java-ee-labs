package edu.ukma.javaee.lab7onwards.utils;

import edu.ukma.javaee.lab7onwards.models.book.Book;
import edu.ukma.javaee.lab7onwards.models.book.BookResponse;

public class BookUtils {
    public static BookResponse toBookResponse(Book book) {
        return BookResponse.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .authors(book.getAuthors())
                .genres(book.getGenres())
                .dateAdded(book.getDateAdded())
                .build();
    }
}
