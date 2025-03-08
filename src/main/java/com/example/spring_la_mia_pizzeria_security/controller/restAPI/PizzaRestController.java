package com.example.spring_la_mia_pizzeria_security.controller.restAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_la_mia_pizzeria_security.model.Pizza;
import com.example.spring_la_mia_pizzeria_security.service.PizzaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PizzaRestController {
    @Autowired
    PizzaService pizzaService;

    @GetMapping("{id}")
    public Pizza get(@PathVariable Integer id) {
        Pizza pizza = pizzaService.findById(id).get();
        return pizza;
    }

    @PostMapping("/addPizza")
    public Pizza store(@Valid @RequestBody Pizza pizza) {
        pizzaService.save(pizza);

        return pizzaService.save(pizza);
    }

    @PutMapping("{id}")
    public Pizza update(@Valid @RequestBody Pizza pizza) {
        return pizzaService.save(pizza);
    }

    @DeleteMapping("{id}")
    public void pizza(@PathVariable Integer id) {
        pizzaService.deleteById(id);
    }

}
