package com.example.spring_la_mia_pizzeria_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.spring_la_mia_pizzeria_security.model.Pizza;
import com.example.spring_la_mia_pizzeria_security.repository.PizzaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    public Optional<Pizza> findById(int Id) {
        return pizzaRepository.findById(Id);
    }

    public List<Pizza> findByNomeContaining(String nome) {
        return pizzaRepository.findByNomeContaining(nome);
    }

    public Pizza save(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public void deleteById(int id) {
        pizzaRepository.deleteById(id);
    }
}
