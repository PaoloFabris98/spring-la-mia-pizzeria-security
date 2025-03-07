package com.example.spring_la_mia_pizzeria_security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_la_mia_pizzeria_security.model.Ingrediente;

import com.example.spring_la_mia_pizzeria_security.repository.IngredientiRepository;

@Service
public class IngredientiService {
    @Autowired
    private IngredientiRepository ingredientiRepository;

    public Ingrediente creaIngrediente(Ingrediente ingrediente) {
        return ingredientiRepository.save(ingrediente);
    }

    public void deleteById(Integer id) {
        ingredientiRepository.deleteById(id);
    }

    public List<Ingrediente> findAll() {
        return ingredientiRepository.findAll();
    }

    public List<Ingrediente> findAllById(List<Integer> ids) {
        return ingredientiRepository.findAllById(ids);
    }

    public Ingrediente getById(Integer id) {
        return ingredientiRepository.findById(id).get();
    }

    public boolean existById(Integer id) {
        return ingredientiRepository.existsById(id);
    }

}
