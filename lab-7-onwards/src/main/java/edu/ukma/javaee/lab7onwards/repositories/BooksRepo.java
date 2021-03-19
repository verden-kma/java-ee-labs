package edu.ukma.javaee.lab7onwards.repositories;

import edu.ukma.javaee.lab7onwards.models.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepo extends JpaRepository<Book, String>, JpaSpecificationExecutor<Book> {

//    @Query()
//    Object mq(@Param("authors") List<String> authors);
}
