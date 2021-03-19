package edu.ukma.javaee.lab7onwards.models.book;

import lombok.Data;

import java.util.List;

@Data
public class BookRequest {
    private String isbn;

    private String title;

    private List<String> authors;

    private List<String> genres;
}
