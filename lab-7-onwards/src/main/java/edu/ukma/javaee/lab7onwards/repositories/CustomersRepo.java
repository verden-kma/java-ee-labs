package edu.ukma.javaee.lab7onwards.repositories;

import edu.ukma.javaee.lab7onwards.models.book.Book;
import edu.ukma.javaee.lab7onwards.models.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomersRepo extends JpaRepository<Customer, String> {
    Customer findByUsername(String username);

    @Query("SELECT cust.favoriteBooks FROM Customer cust WHERE cust.username=:customer_id")
    List<Book> findFavoriteBooks(@Param("customer_id") String username);

    boolean existsByUsernameAndFavoriteBooksContains(String name, Book b);
}
