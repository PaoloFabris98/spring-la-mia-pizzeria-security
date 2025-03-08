package com.example.spring_la_mia_pizzeria_security.security;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class DataSourceConfiguration {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/spring-la-mia-pizzeria-crud")
                .username("Paolo")
                .password("aGbHigy7")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

}
