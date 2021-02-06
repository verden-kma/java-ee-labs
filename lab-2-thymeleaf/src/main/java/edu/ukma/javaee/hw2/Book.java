package edu.ukma.javaee.hw2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    @Id
    private String isbn;
    private String title;
    private String author;
    private LocalDate dateAdded;
}
