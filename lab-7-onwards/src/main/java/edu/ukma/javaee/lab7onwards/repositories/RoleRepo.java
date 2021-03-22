package edu.ukma.javaee.lab7onwards.repositories;

import edu.ukma.javaee.lab7onwards.models.customer.CustomerRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<CustomerRoleEntity, Long> {
    CustomerRoleEntity findByName(String name);
}
