package com.example.spring_la_mia_pizzeria_security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.spring_la_mia_pizzeria_security.model.Allergene;
import com.example.spring_la_mia_pizzeria_security.service.AllergeniService;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AllergeneController {
    @Autowired
    private AllergeniService allergeniService;

    @GetMapping("/creaAllergene")
    public String addAllergene(Model model) {
        Allergene allergene = new Allergene();
        model.addAttribute("allergene", allergene);
        return "allergene/creaAllergene";
    }

    @PostMapping("/creaAllergene")
    public String addAllergene(@Valid @ModelAttribute("allergene") Allergene formAllergene,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "allergene/creaAllergene";
        }

        allergeniService.creaAllergene(formAllergene);
        redirectAttributes.addFlashAttribute("message", "Allergene creato con successo");
        redirectAttributes.addFlashAttribute("messageClass", "alert-success");

        return "redirect:/";
    }

    @GetMapping("/allergeni")
    public String allergeni(Model model) {
        List<Allergene> allergeni = allergeniService.findAll();
        model.addAttribute("allergeni", allergeni);
        return "allergene/allergeni";
    }

    @GetMapping("/editAllergene/{id}")
    public String editAllergene(@PathVariable Integer id, Model model) {
        Allergene allergene = allergeniService.getById(id);
        model.addAttribute("allergene", allergene);
        return "allergene/editAllergene";
    }

    @PostMapping("/cancella_allergene/{id}")
    public String deleteAllergene(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Allergene allergene = allergeniService.getById(id);
        if (allergene != null) {
            allergeniService.deleteById(id);
            redirectAttributes.addFlashAttribute("message",
                    "L'allergene: " + allergene.getNome() + ", è stata cancellata con successo.");
            redirectAttributes.addFlashAttribute("messageClass", "alert-danger");
        }
        return "redirect:/allergeni";
    }

}
