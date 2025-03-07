package com.example.spring_la_mia_pizzeria_security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_la_mia_pizzeria_security.model.Allergene;
import com.example.spring_la_mia_pizzeria_security.repository.AllergeniRepository;

@Service
public class AllergeniService {
    @Autowired
    private AllergeniRepository allergeniRepository;

    public Allergene creaAllergene(Allergene allergene) {
        return allergeniRepository.save(allergene);
    }

    public void deleteById(Integer id) {
        allergeniRepository.deleteById(id);
    }

    public List<Allergene> findAll() {
        return allergeniRepository.findAll();
    }

    public List<Allergene> findAllById(List<Integer> ids) {
        return allergeniRepository.findAllById(ids);
    }

    public Allergene getById(Integer id) {
        return allergeniRepository.findById(id).get();
    }

    public boolean existById(Integer id) {
        return allergeniRepository.existsById(id);
    }
}
