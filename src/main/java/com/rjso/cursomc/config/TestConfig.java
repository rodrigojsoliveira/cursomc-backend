package com.rjso.cursomc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.rjso.cursomc.services.DBService;
import com.rjso.cursomc.services.EmailService;
import com.rjso.cursomc.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;

	@Bean
	public boolean instantiateDatabase() throws Exception {
		dbService.instantiateTestDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}
