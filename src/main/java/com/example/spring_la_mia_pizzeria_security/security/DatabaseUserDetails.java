package com.example.spring_la_mia_pizzeria_security.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.spring_la_mia_pizzeria_security.model.Authorities;
import com.example.spring_la_mia_pizzeria_security.model.User;
import com.example.spring_la_mia_pizzeria_security.repository.AuthoritiesRepository;

public class DatabaseUserDetails implements UserDetails {

    @Autowired
    AuthoritiesRepository authoritiesList;
    private final Integer id;
    private final String username;
    private final String password;
    private final Set<GrantedAuthority> authorities;

    public DatabaseUserDetails(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = new HashSet<GrantedAuthority>();
        List<Authorities> userAuthorities = authoritiesList.findByName(username);
        for (int i = 0; i < userAuthorities.size(); i++) {
            this.authorities.add(new SimpleGrantedAuthority(userAuthorities.get(i).getAuthority()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public Integer getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.getUsername();
    }

}
