package edu.ukma.javaee.lab7onwards.utils;

import edu.ukma.javaee.lab7onwards.models.book.Book;
import edu.ukma.javaee.lab7onwards.models.book.BookResponse;

import java.util.function.BiFunction;

public class BookUtils {
    public static BookResponse toBookResponse(Book book, BiFunction<String, Book, Boolean> checkIfFavorited, String username) {
        boolean isFavorited = username != null
                && checkIfFavorited.apply(username, book);
        return BookResponse.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .authors(book.getAuthors())
                .genres(book.getGenres())
                .dateAdded(book.getDateAdded())
                .isFavorited(isFavorited)
                .build();
    }
}
