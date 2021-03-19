package edu.ukma.javaee.lab7onwards.services.implementations;

import edu.ukma.javaee.lab7onwards.exceptions.NoSuchBookException;
import edu.ukma.javaee.lab7onwards.models.book.BookResponse;
import edu.ukma.javaee.lab7onwards.models.customer.Customer;
import edu.ukma.javaee.lab7onwards.repositories.BooksRepo;
import edu.ukma.javaee.lab7onwards.repositories.CustomersRepo;
import edu.ukma.javaee.lab7onwards.services.ICustomerService;
import edu.ukma.javaee.lab7onwards.utils.BookUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {
    private final CustomersRepo customersRepo;
    private final BooksRepo booksRepo;

    @Override
    public void addFavorite(String username, String isbn) {
        Customer customer = customersRepo.findByUsername(username);
        customer.getFavoriteBooks().add(booksRepo.findById(isbn).orElseThrow(() -> new NoSuchBookException(isbn)));
    }

    @Override
    public List<BookResponse> getFavorites(String username) {
        return customersRepo.findFavoriteBooks(username).stream().map(BookUtils::toBookResponse).collect(Collectors.toList());
    }

    @Override
    public void removeFavorite(String username, String isbn) {
        Customer customer = customersRepo.findByUsername(username);
        customer.getFavoriteBooks().remove(booksRepo.findById(isbn).orElseThrow(() -> new NoSuchBookException(isbn)));
    }
}
