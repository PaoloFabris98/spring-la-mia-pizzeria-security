package com.example.spring_la_mia_pizzeria_security.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Allergene {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private String nome;

    private String img;

    @ManyToMany(mappedBy = "allergeni")
    private List<Ingrediente> ingredienti;

    public Allergene() {
    }

    public Allergene(int id, String nome, String img) {
        this.id = id;
        this.nome = nome;
        this.img = img;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
