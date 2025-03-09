package com.example.spring_la_mia_pizzeria_security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.spring_la_mia_pizzeria_security.model.Allergene;
import com.example.spring_la_mia_pizzeria_security.model.Ingrediente;
import com.example.spring_la_mia_pizzeria_security.service.AllergeniService;
import com.example.spring_la_mia_pizzeria_security.service.IngredientiService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngredientiController {

    @Autowired
    private IngredientiService ingredientiService;
    @Autowired
    private AllergeniService allergeniService;

    @GetMapping("/creaIngrediente")
    public String addIngrediente(Model model) {
        Ingrediente ingrediente = new Ingrediente();
        List<Allergene> allergeni = allergeniService.findAll();
        model.addAttribute("allergeni", allergeni);
        model.addAttribute("ingrediente", ingrediente);
        return "ingredienti/creaIngrediente";
    }

    @PostMapping("/creaIngrediente")
    public String addIngrediente(@Valid @ModelAttribute("formIngredienti") Ingrediente formIngredienti,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "ingredienti/creaIngrediente";
        }

        ingredientiService.creaIngrediente(formIngredienti);
        redirectAttributes.addFlashAttribute("message", "L'ingrediente è stato creato con successo.");
        redirectAttributes.addFlashAttribute("messageClass", "alert-success");

        return "redirect:/";
    }

    @GetMapping("/vediIngredienti")
    public String showIngredienti(Model model) {
        List<Ingrediente> ingredienti = ingredientiService.findAll();
        model.addAttribute("ingredienti", ingredienti);
        return "ingredienti/ingredienti";
    }

    @GetMapping("/modificaIngrediente/{id}")
    public String editIngrediente(@PathVariable Integer id, Model model) {
        Ingrediente ingrediente = ingredientiService.findById(id).get();
        List<Allergene> allergeni = allergeniService.findAll();
        if (ingrediente != null) {
            model.addAttribute("allergeni", allergeni);
            model.addAttribute("ingrediente", ingrediente);
            return "ingredienti/editIngrediente";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/modificaIngrediente/{id}")
    public String editIngrediente(@Valid @ModelAttribute("formIngredienti") Ingrediente formIngredienti,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "ingredienti/editIngrediente";
        }

        ingredientiService.save(formIngredienti);

        redirectAttributes.addFlashAttribute("message", "Ingrediente modificato con successo");
        redirectAttributes.addFlashAttribute("messageClass", "alert-success");

        return "redirect:/";
    }

    @PostMapping("/cancella_ingrediente/{id}")
    public String deleteIngrediente(@PathVariable Integer id) {
        ingredientiService.deleteById(id);
        return "redirect:/";
    }

}
