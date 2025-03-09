package com.example.spring_la_mia_pizzeria_security.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.cglib.core.Local;

import com.example.spring_la_mia_pizzeria_security.utilityFunction.EmailValidator;
import com.example.spring_la_mia_pizzeria_security.utilityFunction.EtàValidator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Lo username dell'utente non può essere vuoto")
    private String username;

    @NotBlank(message = "La password dell'utente non può essere vuota")
    private String password;

    private LocalDate dataIscrizione = LocalDate.now();

    private String email;

    @Column(name = "eta", nullable = true)
    private LocalDate età;

    @NotBlank(message = "L'abilitazione dell'utente non può essere vuota")
    private Boolean enabled = true;

    public User() {
    }

    public User(Integer id, String username, String password, String email, LocalDate età) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setEtà(età);
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = EmailValidator.emailValidator(email);
        ;
    }

    public LocalDate getEtà() {
        return this.età;
    }

    public void setEtà(LocalDate età) {
        this.età = età;
    }

    public Boolean isEnabled() {
        return this.enabled;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEighteen() {
        return EtàValidator.età(this.età);
    }

    @PrePersist
    public void prePersist() {
        if (this.dataIscrizione == null) {
            this.dataIscrizione = LocalDate.now();
        }
    }

    public String etàFormattata() {
        DateTimeFormatter formatted = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return this.getEtà().format(formatted);
    }

    @Override
    public String toString() {
        return this.username;
    }
}
