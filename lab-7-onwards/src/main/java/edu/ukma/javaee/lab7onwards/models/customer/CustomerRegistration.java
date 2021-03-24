package edu.ukma.javaee.lab7onwards.models.customer;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CustomerRegistration {
    @Email(message = "the email must be an email :/")
    private String email;

    @Pattern(regexp = "\\w+", message = "only alphanumeric characters are allowed for login")
    private String username;

    @Size(min = 8, max = 20, message = "the password should be in range [8;20]")
    private CharSequence password;
    private CharSequence passwordMatch;
}
