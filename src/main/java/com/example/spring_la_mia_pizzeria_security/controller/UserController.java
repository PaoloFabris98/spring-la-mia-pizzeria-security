package com.example.spring_la_mia_pizzeria_security.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring_la_mia_pizzeria_security.model.Pizza;
import com.example.spring_la_mia_pizzeria_security.model.SpecialOffers;
import com.example.spring_la_mia_pizzeria_security.model.User;
import com.example.spring_la_mia_pizzeria_security.repository.UserRepository;

import jakarta.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/utenti")
    public String seeUsers(Model model) {
        List<User> utenti = userRepository.findAll();
        model.addAttribute("utenti", utenti);
        return "utenti/utenti";
    }

    @GetMapping("/registraUtente")
    public String registrazione(Model model) {
        User user = new User();
        user.setDataIscrizione();
        model.addAttribute("user", user);
        return "utenti/addUser";
    }

    @PostMapping("/registraUtente")
    public String registrazione(@Valid @ModelAttribute("utente") User formUser, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "utenti/addUser";
        }

        redirectAttributes.addFlashAttribute("message", "La tua pizza è stata creata");
        redirectAttributes.addFlashAttribute("messageClass", "alert-success");

        return "redirect:/";
    }

}
