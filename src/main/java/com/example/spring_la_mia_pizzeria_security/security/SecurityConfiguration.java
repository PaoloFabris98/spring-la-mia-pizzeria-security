package com.example.spring_la_mia_pizzeria_security.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/webjars/**", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/creaPizza", "/creaOfferta/**", "/edit/**", "/creaIngrediente", "/creaAllergene",
                        "/editAllergene/**")
                .hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/creaPizza", "/creaOfferta/**", "/edit/**", "/delete/**",
                        "/creaIngrediente", "/cancella_ingrediente/**", "/creaAllergene", "/editAllergene/**",
                        "/cancella_allergene/**")
                .hasAuthority("ADMIN")
                .requestMatchers("/allergeni", "/pizza", "/**", "/vediIngredienti").hasAnyAuthority("ADMIN", "USER"))
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll())
                .exceptionHandling(ex -> ex.accessDeniedPage("/access-denied"));

        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        return manager;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

}
