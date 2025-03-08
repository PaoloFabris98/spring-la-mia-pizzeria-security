package com.example.spring_la_mia_pizzeria_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    @SuppressWarnings("removal")
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/creaPizza", "/creaOfferta/**", "/edit/**", "/creaIngrediente", "/creaAllergene",
                        "/editAllergene/**")
                .hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/creaPizza").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/creaOfferta/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/creaOfferta/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/edit/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/delete/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/creaIngrediente").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/cancella_ingrediente/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/creaAllergene").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/editAllergene/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/cancella_allergene/**").hasAuthority("ADMIN")
                .requestMatchers("/allergeni", "/pizza", "/**", "/vediIngredienti").hasAnyAuthority("ADMIN", "USER")
                .and().formLogin()
                .and().logout()
                .and().exceptionHandling();
        return http.build();
    }

    @Bean
    DatabaseUserDetailsService userDetailsService() {
        return new DatabaseUserDetailsService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

}
