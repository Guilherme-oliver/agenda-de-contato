package com.guilherme.Agenda.config;

import com.guilherme.Agenda.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("teste")
public class TesteConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateTestDatabase() throws ParseException{
        dbService.instantiateTestDatabase();
        return true;
    }
}
