package edu.ukma.javaee.lab7onwards.services.implementations;

import edu.ukma.javaee.lab7onwards.models.*;
import edu.ukma.javaee.lab7onwards.repositories.BooksRepo;
import edu.ukma.javaee.lab7onwards.services.IBookService;
import edu.ukma.javaee.lab7onwards.specifications.BookSpecs;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService implements IBookService {
    private final BooksRepo booksRepo;

    @Override
    public void addBook(BookRequest bookRequest) {
        Book newBook = Book.builder()
                .isbn(bookRequest.getIsbn())
                .title(bookRequest.getTitle())
                .authors(bookRequest.getAuthors())
                .genres(bookRequest.getGenres().stream().map(Genre::valueOf).collect(Collectors.toList()))
                .dateAdded(LocalDate.now())
                .build();
        booksRepo.save(newBook);
    }

    @Override
    public BookResponse getBook(String isbn) {
        Optional<Book> maybeBook = booksRepo.findById(isbn);
        return maybeBook.map(book -> BookResponse.builder()
                .authors(book.getAuthors())
                .isbn(isbn)
                .title(book.getTitle())
                .genres(book.getGenres())
                .dateAdded(book.getDateAdded())
                .build()).orElse(null);
    }

    @Override
    public List<BookResponse> getBooks(PageRequest pr, @Nullable BookFilter bookFilter) {
        Specification<Book> specification = Specification.where(null);
        if (bookFilter != null) {
            if (bookFilter.getTitlePrefix() != null)
                specification = specification.and(BookSpecs.getBooksByTitle(bookFilter.getTitlePrefix()));
//            if (bookFilter.getAuthors() != null) {
//                specification = specification.and(BookSpecs.getBooksByAuthors(bookFilter.getAuthors()));
//            }
//            if (bookFilter.getGenres() != null) {
//                specification = specification.and(BookSpecs.getBooksByGeneres(bookFilter.getGenres()));
//            }
        }
        return booksRepo.findAll(specification, pr).stream()
                .map(BookService::toBookResponse).collect(Collectors.toList());
    }

    private static BookResponse toBookResponse(Book book) {
        return BookResponse.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .authors(book.getAuthors())
                .genres(book.getGenres())
                .dateAdded(book.getDateAdded())
                .build();
    }
}
