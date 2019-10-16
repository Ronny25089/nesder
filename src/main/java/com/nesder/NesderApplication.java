package com.nesder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class })
public class NesderApplication {

	public static void main(String[] args) {
		SpringApplication.run(NesderApplication.class, args);
	}

}


