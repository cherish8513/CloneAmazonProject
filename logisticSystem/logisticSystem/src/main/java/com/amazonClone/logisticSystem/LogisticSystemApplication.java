package com.amazonClone.logisticSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LogisticSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogisticSystemApplication.class, args);
	}

}
