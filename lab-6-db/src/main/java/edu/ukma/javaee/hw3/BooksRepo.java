package edu.ukma.javaee.hw3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepo extends JpaRepository<Book, String> {
    List<Book> findAllByTitleStartsWithIgnoreCase(String titlePrefix);
}
