package edu.ukma.javaee.lab7onwards.exceptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NoSuchBookException extends RuntimeException {
    private final String missingIsbn;

    @Override
    public String getMessage() {
        return String.format("Missing book with isbn %s.", missingIsbn);
    }
}
