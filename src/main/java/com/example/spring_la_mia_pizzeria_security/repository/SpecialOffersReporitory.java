package com.example.spring_la_mia_pizzeria_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_la_mia_pizzeria_security.model.SpecialOffers;

@Repository
public interface SpecialOffersReporitory extends JpaRepository<SpecialOffers, Integer> {

}
