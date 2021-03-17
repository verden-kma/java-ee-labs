package edu.ukma.javaee.lab7onwards.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    private String isbn;

    private String title;

    private LocalDate dateAdded;

    @ElementCollection
    @CollectionTable(name = "book_to_author", joinColumns = @JoinColumn(name = "book_id"))
    private List<String> authors;

    @ElementCollection(targetClass = Genre.class)
    @CollectionTable(name = "book_genre", joinColumns = @JoinColumn(name = "book_id"))
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;

    @ManyToMany(mappedBy = "favoriteBooks", fetch = FetchType.LAZY)
    private List<Customer> customers;
}
