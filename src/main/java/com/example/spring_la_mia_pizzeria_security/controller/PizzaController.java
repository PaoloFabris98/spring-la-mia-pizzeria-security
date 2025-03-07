package com.example.spring_la_mia_pizzeria_security.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import com.example.spring_la_mia_pizzeria_security.model.Ingrediente;
import com.example.spring_la_mia_pizzeria_security.model.Pizza;
import com.example.spring_la_mia_pizzeria_security.model.SpecialOffers;
import com.example.spring_la_mia_pizzeria_security.service.IngredientiService;
import com.example.spring_la_mia_pizzeria_security.service.PizzaService;
import com.example.spring_la_mia_pizzeria_security.service.SpecialOffersService;

@Controller
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;
    @Autowired
    SpecialOffersService specialOffersService;
    @Autowired
    IngredientiService ingredientiService;

    @GetMapping("/pizza")
    public String seePizza(@RequestParam(name = "query") String query, Model model, HttpServletRequest request) {
        Pizza pizza = pizzaService.findByNomeContaining(query).stream().findFirst().orElse(null);

        if (pizza == null) {
            model.addAttribute("message", "Pizza non trovata");
        } else {
            model.addAttribute("currentURI", request.getRequestURI());
            model.addAttribute("pizzas", pizza);
        }

        return "pizza/pizza";
    }

    @GetMapping("/searchPizza")
    public String searchPizza(@RequestParam(name = "pizzas") String pizzas, Model model, HttpServletRequest request) {
        List<Pizza> pizze = pizzaService.findByNomeContaining(pizzas);
        if (pizzas.isEmpty()) {
            return "redirect:/";
        } else {
            model.addAttribute("currentURI", request.getRequestURI());
            model.addAttribute("pizzas", pizze);
            return "pizza/pizza";
        }
    }

    @GetMapping("/creaPizza")
    public String addPizza(Model model) {
        List<Ingrediente> ingredienti = ingredientiService.findAll();
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredienti", ingredienti);

        return "pizza/addPizza";
    }

    @GetMapping("/creaOfferta/{id}")
    public String addOfferta(@PathVariable Integer id, Model model) {
        SpecialOffers offerta = new SpecialOffers();

        offerta.setPizza(pizzaService.findById(id).get());
        offerta.setDiscountStart(LocalDate.now());

        model.addAttribute("offerta", offerta);

        return "offerta/offerta";
    }

    @PostMapping("/creaPizza")
    public String addPizza(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "pizza/addPizza";
        }

        pizzaService.save(formPizza);

        redirectAttributes.addFlashAttribute("message", "La tua pizza è stata creata");
        redirectAttributes.addFlashAttribute("messageClass", "alert-success");

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editPizza(@PathVariable Integer id, Model model) {
        Optional<Pizza> pizza = pizzaService.findById(id);
        List<Ingrediente> ingredienti = ingredientiService.findAll();
        if (pizza.isPresent()) {
            model.addAttribute("ingredienti", ingredienti);
            model.addAttribute("pizza", pizza.get());
            return "pizza/editPizza";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/edit/{id}")
    public String editPizza(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "pizza/editPizza";
        }

        pizzaService.save(formPizza);

        redirectAttributes.addFlashAttribute("message", "Pizza modificata con successo");
        redirectAttributes.addFlashAttribute("messageClass", "alert-success");

        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deletePizza(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Pizza> pizza = pizzaService.findById(id);
        if (pizza.isPresent()) {
            pizzaService.deleteById(id);
            redirectAttributes.addFlashAttribute("message",
                    "La pizza: " + pizza.get().getNome() + ", è stata cancellata con successo.");
            redirectAttributes.addFlashAttribute("messageClass", "alert-danger");
        }
        return "redirect:/";
    }
}