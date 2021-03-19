package edu.ukma.javaee.lab7onwards.models.customer;

import org.springframework.security.core.GrantedAuthority;

public enum CustomerPermission implements GrantedAuthority {

    MNG_FAVORITES,
    ADD_BOOKS;

    @Override
    public String getAuthority() {
        return name();
    }
}
