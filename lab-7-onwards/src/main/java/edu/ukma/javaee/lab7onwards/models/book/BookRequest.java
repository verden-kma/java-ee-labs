package edu.ukma.javaee.lab7onwards.models.book;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class BookRequest {
    @Pattern(regexp = "((978-)|(979-)|ISBN )?\\d-\\d{2}-\\d{6}-\\d", message = "isbn does not match pattern")
    private String isbn;

    @NotBlank(message = "title must not be blank")
    private String title;

    @NotEmpty(message = "at leas one author must be specified")
    private List<@NotBlank String> authors;

    @NotEmpty(message = "valid genres must be entered")
    private List<String> genres;
}
