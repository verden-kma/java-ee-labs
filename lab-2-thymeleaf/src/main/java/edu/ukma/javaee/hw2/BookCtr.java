package edu.ukma.javaee.hw2;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class BookCtr {
    private final BooksRepo bookRepo;

    @Autowired
    public BookCtr(BooksRepo repo) {
        this.bookRepo = repo;
    }

    @GetMapping("/")
    public String getMainPage() {
        return "add-book";
    }

    @PostMapping("/book")
    public String addBook(@ModelAttribute BookModel model) {
        Book book = new Book();
        BeanUtils.copyProperties(model, book);
        book.setDateAdded(LocalDate.now());
        bookRepo.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("books", bookRepo.findAll());
        return "view-books";
    }
}


