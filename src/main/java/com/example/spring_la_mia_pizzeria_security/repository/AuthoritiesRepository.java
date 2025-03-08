package com.example.spring_la_mia_pizzeria_security.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.spring_la_mia_pizzeria_security.model.Authorities;

@Repository
public interface AuthoritiesRepository {
    List<Authorities> findByName(String username);
}
