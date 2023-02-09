package com.springboot.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.springboot.services.DBService;

@Configuration
@Profile("dev")
public class TestConfig {
	
	@Autowired
	private DBService dbService;

	@Bean
	boolean InstantiateDatabase() throws ParseException {
		dbService.instatiateTestDatabase();
		return true;
	}
	
}
