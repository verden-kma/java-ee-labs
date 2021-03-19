package edu.ukma.javaee.lab7onwards.services;

import edu.ukma.javaee.lab7onwards.models.book.BookResponse;

import java.util.List;

public interface ICustomerService {
    void addFavorite(String username, String isbn);

    List<BookResponse> getFavorites(String username);

    void removeFavorite(String username, String isbn);
}
