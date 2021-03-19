package edu.ukma.javaee.lab7onwards.controllers;

import edu.ukma.javaee.lab7onwards.models.book.BookResponse;
import edu.ukma.javaee.lab7onwards.services.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller("books/favorites")
@PreAuthorize("hasAuthority(MNG_FAVORITES)")
@RequiredArgsConstructor
public class Favorites {
    private final ICustomerService customerService;

    @PostMapping("/books/favorites")
    public void addToFavorite(@RequestParam String isbn, Principal principal) {
        customerService.addFavorite(principal.getName(), isbn);
    }

    @GetMapping("/books/favorites")
    public String getFavorites(Model model, Principal principal) {
        List<BookResponse> books = customerService.getFavorites(principal.getName());
        model.addAttribute("favorites", books);
        return "/favorite-books";
    }

    @DeleteMapping("/books/favorites")
    public void deleteFromFavorites(@RequestParam String isbn, Principal principal) {
        customerService.removeFavorite(principal.getName(), isbn);

    }
}
