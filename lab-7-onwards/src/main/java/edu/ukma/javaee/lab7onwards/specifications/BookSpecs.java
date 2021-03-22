package edu.ukma.javaee.lab7onwards.specifications;

import edu.ukma.javaee.lab7onwards.models.book.Book;
import edu.ukma.javaee.lab7onwards.models.book.Book_;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecs {
    public static Specification<Book> getBooksByTitle(String titlePrefix) {
        return (root, query, cb) ->
                cb.like(cb.upper(root.get(Book_.TITLE)), titlePrefix.toUpperCase() + "%");
    }

//    public static Specification<Book> getBooksByAuthors(List<String> authors) {
//        return (root, query, cb) -> {
//            Join<Book, String> bookAuthors = root.join(Book_.AUTHORS, JoinType.INNER);
//            bookAuthors = bookAuthors.on(cb.equal(bookAuthors.get(Book_.ISBN),
//                    bookAuthors.get("book_id")));
//            return bookAuthors.get(Book_.AUTHORS).in(authors);
//        };
//    }
//
//    public static Specification<Book> getBooksByGeneres(List<Genre> genres) {
//
//    }
}
