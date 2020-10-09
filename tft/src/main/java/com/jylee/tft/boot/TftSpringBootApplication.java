package com.jylee.tft.boot;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages="com.jylee.tft")
@EnableAsync
@EnableScheduling
@EntityScan("com.jylee.tft.dao")
@EnableJpaRepositories("com.jylee.ftf.repository")
public class TftSpringBootApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@PersistenceContext
	EntityManager entityManager;
	  
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(TftSpringBootApplication.class);
	}
	
	public static void main(String[] args) {
		
		new SpringApplicationBuilder(TftSpringBootApplication.class).run(args);		
	}

	@Override
	public void run(String... args) throws Exception {
		
	}	

}