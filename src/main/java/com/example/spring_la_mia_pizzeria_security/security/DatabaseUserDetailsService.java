package com.example.spring_la_mia_pizzeria_security.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.spring_la_mia_pizzeria_security.repository.UserRepository;
import com.example.spring_la_mia_pizzeria_security.model.User;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userAttempt = repository.findByUsername(username);
        if (userAttempt.isEmpty()) {
            throw new UsernameNotFoundException("No user with this username");
        }
        return new DatabaseUserDetails(userAttempt.get());

    }

    public List<User> findAll() {
        return repository.findAll();
    }

}
