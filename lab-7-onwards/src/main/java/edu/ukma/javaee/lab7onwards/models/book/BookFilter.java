package edu.ukma.javaee.lab7onwards.models.book;

import lombok.Data;

import java.util.List;

@Data
public class BookFilter {
    private String titlePrefix;
    private List<String> authors;
    private List<Genre> genres;
}
