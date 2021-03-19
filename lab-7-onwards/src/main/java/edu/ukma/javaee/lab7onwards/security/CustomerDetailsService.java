package edu.ukma.javaee.lab7onwards.security;

import edu.ukma.javaee.lab7onwards.models.customer.Customer;
import edu.ukma.javaee.lab7onwards.models.customer.CustomerPermissionEntity;
import edu.ukma.javaee.lab7onwards.repositories.CustomersRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerDetailsService implements UserDetailsService {
    private final CustomersRepo customersRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Customer customer = customersRepo.findByUsername(s);
        return User.builder()
                .username(customer.getUsername())
                .password(customer.getEnpassword())
                .authorities(customer.getRole().getPermissions().stream().map(CustomerPermissionEntity::getPermission).collect(Collectors.toList()))
                .build();
    }

    // @Bean UserDetails
}
