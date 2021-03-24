package edu.ukma.javaee.lab7onwards.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Pages {

    @GetMapping("/")
    public String getMainPage() {
        return "/index";
    }
}
