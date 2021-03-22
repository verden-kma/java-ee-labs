package edu.ukma.javaee.lab7onwards.models.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private String isbn;

    private String title;

    private List<String> authors;

    private List<Genre> genres;

    private LocalDate dateAdded;

    @Nullable
    private Boolean isFavorited;
}
