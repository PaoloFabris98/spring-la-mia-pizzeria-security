package com.example.spring_la_mia_pizzeria_security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring_la_mia_pizzeria_security.model.Pizza;
import com.example.spring_la_mia_pizzeria_security.repository.PizzaRepository;
import com.example.spring_la_mia_pizzeria_security.service.PizzaService;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/")
    public String index(Model model) {
        if (pizzaRepository.findAll().size() == 0) {
            List<Pizza> pizzas = pizzaService.getAllPizzas();
            model.addAttribute("pizzas", pizzas);
        } else {
            List<Pizza> pizzas = pizzaService.getAllPizzas();
            model.addAttribute("pizzas", pizzas);
        }
        ;
        return "index/index";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "index/accessDenied";
    }

    @GetMapping("/contatti")
    public String contatti(Model model) {
        return "index/contatti";
    }
}
