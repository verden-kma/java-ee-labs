package edu.ukma.javaee.lab7onwards.repositories;

import edu.ukma.javaee.lab7onwards.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepo extends JpaRepository<Customer, String> {
}
