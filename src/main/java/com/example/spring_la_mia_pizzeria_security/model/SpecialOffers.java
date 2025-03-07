package com.example.spring_la_mia_pizzeria_security.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class SpecialOffers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    private String descrizione;

    private double fixedDiscount = 0.0;

    private LocalDate discountStart;

    private LocalDate discountEnd;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    public SpecialOffers() {
    }

    public SpecialOffers(int id, String descrizione, int fixedDiscount, LocalDate discountStart,
            LocalDate discountEnd) {
        this.id = id;
        this.descrizione = descrizione;
        this.fixedDiscount = fixedDiscount;
        this.discountStart = discountStart;
        this.discountEnd = discountEnd;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getFixedDiscount() {
        return this.fixedDiscount;
    }

    public void setFixedDiscount(double fixedDiscount) {
        this.fixedDiscount = fixedDiscount;
    }

    public LocalDate getDiscountStart() {
        return this.discountStart;
    }

    public void setDiscountStart(LocalDate discountStart) {
        this.discountStart = discountStart;
    }

    public LocalDate getDiscountEnd() {
        return this.discountEnd;
    }

    public void setDiscountEnd(LocalDate discountEnd) {
        this.discountEnd = discountEnd;
    }

    public Pizza getPizza() {
        return this.pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

}
