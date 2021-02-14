package edu.ukma.javaee.hw3;

import edu.ukma.javaee.hw3.exceptions.BookDuplicationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class BookCtr {
    private final BooksRepo bookRepo;

    @Autowired
    public BookCtr(BooksRepo repo) {
        this.bookRepo = repo;
    }

    @PostMapping("/book")
    @ResponseBody
    public void addBook(@ModelAttribute BookModel model) {
        Book book = new Book();
        BeanUtils.copyProperties(model, book);
        book.setDateAdded(LocalDate.now());
        if (bookRepo.existsById(book.getIsbn())) throw new BookDuplicationException("Book with provide isbn already exists.");
        bookRepo.save(book);
    }

    @GetMapping("/")
    public String getMainPage(/*Model model*/) {
//        model.addAttribute("books", bookRepo.findAll());
        return "index";
    }

    @GetMapping("/books")
    @ResponseBody
    public List<Book> getBooks() {
        return bookRepo.findAll();
    }

    @GetMapping("/search/title")
    @ResponseBody
    public List<Book> findByTitle(@RequestParam String title) {
        return bookRepo.findAllByTitleStartsWithIgnoreCase(title);
    }

    @GetMapping("/search/isbn")
    @ResponseBody
    public Optional<Book> findByISBN(@RequestParam String isbn) {
        return bookRepo.findById(isbn);
    }
}