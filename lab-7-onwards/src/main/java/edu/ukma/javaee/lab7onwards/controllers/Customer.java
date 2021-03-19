package edu.ukma.javaee.lab7onwards.controllers;

import edu.ukma.javaee.lab7onwards.models.customer.CustomerRegistration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class Customer {
    // todo: from frontend send to "/login" and let spring handle session, auth status etc.
    @GetMapping("/register")
    public String getSignupForm() {
        return "registration";
    }

    @PostMapping("/customers")
    public void addUser(@RequestBody CustomerRegistration registration) {

    }

}
