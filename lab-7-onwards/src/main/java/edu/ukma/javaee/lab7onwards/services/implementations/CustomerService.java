package edu.ukma.javaee.lab7onwards.services.implementations;

import edu.ukma.javaee.lab7onwards.exceptions.NoSuchBookException;
import edu.ukma.javaee.lab7onwards.models.book.BookResponse;
import edu.ukma.javaee.lab7onwards.models.customer.Customer;
import edu.ukma.javaee.lab7onwards.models.customer.CustomerRegistration;
import edu.ukma.javaee.lab7onwards.models.customer.CustomerRole;
import edu.ukma.javaee.lab7onwards.repositories.BooksRepo;
import edu.ukma.javaee.lab7onwards.repositories.CustomersRepo;
import edu.ukma.javaee.lab7onwards.repositories.RoleRepo;
import edu.ukma.javaee.lab7onwards.services.ICustomerService;
import edu.ukma.javaee.lab7onwards.utils.BookUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {
    private final CustomersRepo customersRepo;
    private final BooksRepo booksRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void addCustomer(CustomerRegistration registration) {
        // to Oleg: I've googled MapStruct, it seems like an overkill here
        Customer customer = new Customer();
        BeanUtils.copyProperties(registration, customer);
        customer.setEnpassword(passwordEncoder.encode(registration.getPassword()));
        customer.setFavoriteBooks(new ArrayList<>());
        customer.setRole(roleRepo.findByName(CustomerRole.CUSTOMER.name()));
        customersRepo.save(customer);
    }

    @Override
    public void addFavorite(String username, String isbn) {
        Customer customer = customersRepo.findByUsername(username);
        customer.getFavoriteBooks().add(booksRepo.findById(isbn).orElseThrow(() -> new NoSuchBookException(isbn)));
        customersRepo.save(customer);
    }

    @Override
    public List<BookResponse> getFavorites(String username) {
        return customersRepo.findFavoriteBooks(username).stream()
                .map(x -> BookUtils.toBookResponse(x, customersRepo::existsByUsernameAndFavoriteBooksContains, username)).collect(Collectors.toList());
    }

    @Override
    public void removeFavorite(String username, String isbn) {
        Customer customer = customersRepo.findByUsername(username);
        customer.getFavoriteBooks().remove(booksRepo.findById(isbn).orElseThrow(() -> new NoSuchBookException(isbn)));
        customersRepo.save(customer);
    }
}
