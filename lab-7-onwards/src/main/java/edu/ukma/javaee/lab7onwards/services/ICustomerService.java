package edu.ukma.javaee.lab7onwards.services;

import edu.ukma.javaee.lab7onwards.models.book.BookResponse;
import edu.ukma.javaee.lab7onwards.models.customer.CustomerRegistration;

import java.util.List;

public interface ICustomerService {
    void addCustomer(CustomerRegistration registration);

    void addFavorite(String username, String isbn);

    List<BookResponse> getFavorites(String username);

    void removeFavorite(String username, String isbn);
}
