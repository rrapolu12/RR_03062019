package com.ram.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication 
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.ram.crud"})
@EnableJpaRepositories(basePackages="com.ram.crud.repositories")
@EnableTransactionManagement
@EntityScan(basePackages="com.ram.crud.entities")
public class CardApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardApplication.class, args);
    }
    
}
