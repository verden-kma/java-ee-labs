package edu.ukma.javaee.lab7onwards.models.customer;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CustomerPermissionEntity {
    @Id
    @Column(name = "permission_id")
    private long id;

    @Enumerated(EnumType.STRING)
    private CustomerPermission permission;
}
