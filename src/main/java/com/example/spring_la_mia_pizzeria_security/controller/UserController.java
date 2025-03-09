package com.example.spring_la_mia_pizzeria_security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.spring_la_mia_pizzeria_security.model.User;
import com.example.spring_la_mia_pizzeria_security.repository.UserRepository;

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

}
