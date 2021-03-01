package edu.ukma.javaee.hw3.exceptions;

public class BookDuplicationException extends RuntimeException {
    public BookDuplicationException(String cause) {
        super(cause);
    }
}
