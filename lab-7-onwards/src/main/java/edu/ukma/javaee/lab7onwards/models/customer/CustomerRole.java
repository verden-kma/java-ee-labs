package edu.ukma.javaee.lab7onwards.models.customer;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class CustomerRole {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany
    @JoinTable(name = "role_to_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<CustomerPermissionEntity> permissions;
}
