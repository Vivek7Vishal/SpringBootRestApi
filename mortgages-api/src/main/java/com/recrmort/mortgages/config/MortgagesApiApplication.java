package com.recrmort.mortgages.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/**
 * 
 * Spring boot application starter class
 * scan the application packages 
 * scans the mortage repository class for jpa configurations to start the in memory db
 * scans the model class to instantiate it with interest rate values
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.recrmort.mortgages" })
@EnableJpaRepositories(basePackages = { "com.recrmort.mortgages.repository" })
@EntityScan(basePackages = { "com.recrmort.mortgages.model" })
public class MortgagesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MortgagesApiApplication.class, args);
	}

}
