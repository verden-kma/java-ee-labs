package edu.ukma.javaee.lab7onwards.controllers;

import edu.ukma.javaee.lab7onwards.repositories.CustomersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Pages {
    @Autowired
    CustomersRepo repo;

    @GetMapping("/")
    public String getMainPage() {
        return "/index";
    }
}
