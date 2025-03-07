package com.example.spring_la_mia_pizzeria_security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_la_mia_pizzeria_security.model.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    public List<Pizza> findByNomeContaining(String name);
}
