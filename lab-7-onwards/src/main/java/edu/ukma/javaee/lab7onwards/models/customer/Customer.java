package edu.ukma.javaee.lab7onwards.models.customer;

import edu.ukma.javaee.lab7onwards.models.book.Book;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    private String username;
    private String email;
    private String enpassword;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "customer_to_book", joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> favoriteBooks;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private CustomerRoleEntity role;
}
