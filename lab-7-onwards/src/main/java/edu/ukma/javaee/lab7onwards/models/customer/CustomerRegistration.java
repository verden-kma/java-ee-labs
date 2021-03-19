package edu.ukma.javaee.lab7onwards.models.customer;

import lombok.Data;

@Data
public class CustomerRegistration {
    private String email;
    private String username;
    private String password;
}
