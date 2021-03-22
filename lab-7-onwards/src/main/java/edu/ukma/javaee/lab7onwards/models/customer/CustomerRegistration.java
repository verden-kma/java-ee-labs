package edu.ukma.javaee.lab7onwards.models.customer;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CustomerRegistration {
    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private CharSequence password;
    private CharSequence passwordMatch;
}
