package edu.ukma.javaee.lab7onwards.controllers;

import edu.ukma.javaee.lab7onwards.models.book.BookResponse;
import edu.ukma.javaee.lab7onwards.services.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@PreAuthorize("hasAuthority(MNG_FAVORITES)")
@RestController("/books/favorites")
@RequiredArgsConstructor
public class Favorites {
    private final ICustomerService customerService;

    @PostMapping("/{isbn}")
    public void addToFavorite(@PathVariable String isbn, Principal principal) {
        customerService.addFavorite(principal.getName(), isbn);
    }

    @GetMapping
    public List<BookResponse> getFavorites(Principal principal) {
        return customerService.getFavorites(principal.getName());
    }

    @DeleteMapping("/{isbn}")
    public void deleteFromFavorites(@PathVariable String isbn, Principal principal) {
        customerService.removeFavorite(principal.getName(), isbn);
    }
}
