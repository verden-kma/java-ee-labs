package edu.ukma.javaee.lab7onwards.controllers;

import edu.ukma.javaee.lab7onwards.models.customer.CustomerRegistration;
import edu.ukma.javaee.lab7onwards.services.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Validated
public class CustomerController {
    private final ICustomerService customerService;

    @GetMapping("/register")
    public String getSignupForm(Model model) {
        CustomerRegistration reg = new CustomerRegistration();
        model.addAttribute("customer", reg);
        return "registration";
    }

    @PostMapping("/customers")
    public String addUser(@Valid @ModelAttribute CustomerRegistration registration, HttpServletResponse resource) {
        if (!registration.getPassword().equals(registration.getPasswordMatch()))
            resource.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        else customerService.addCustomer(registration);
        return "/index";
    }

}
