package edu.ukma.javaee.hw3.exceptions;

public class NoSuchBookException extends RuntimeException {
    public NoSuchBookException(String isbn) {
        super(String.format("No book with id %s found", isbn));
    }
}
