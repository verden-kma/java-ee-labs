package edu.ukma.javaee.lab7onwards.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class Pages {
    @GetMapping
    public RedirectView getMainPage() {
        return new RedirectView("/books");
    }
}
