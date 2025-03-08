package com.example.spring_la_mia_pizzeria_security.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private String nome;

    // @NotEmpty
    // private String tipo;

    @ManyToMany(mappedBy = "ingredienti")
    private List<Pizza> pizze;

    @ManyToMany
    @JoinTable(name = "allergene_ingrediente", joinColumns = @JoinColumn(name = "allergene_id"), inverseJoinColumns = @JoinColumn(name = "ingrediente_id"))
    private List<Allergene> allergeni;

    public Ingrediente() {
    }

    public Ingrediente(String nomeIngrediente) {
        this.nome = nomeIngrediente;
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

    public List<Pizza> getPizze() {
        return this.pizze;
    }

    public void setPizze(List<Pizza> pizze) {
        this.pizze = pizze;
    }

    public List<Allergene> getAllergeni() {
        return this.allergeni;
    }

    public void setAllergeni(List<Allergene> allergeni) {
        this.allergeni = allergeni;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
