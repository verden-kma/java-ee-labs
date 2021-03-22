package edu.ukma.javaee.lab7onwards.models.customer;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "customer_role")
public class CustomerRoleEntity {
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private long id;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_to_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<CustomerPermissionEntity> permissions;
}

