package edu.ukma.javaee.hw2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepo extends JpaRepository<Book, String> {
}
