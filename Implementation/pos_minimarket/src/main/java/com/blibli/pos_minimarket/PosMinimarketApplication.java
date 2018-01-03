package com.blibli.pos_minimarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PosMinimarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosMinimarketApplication.class, args);
	}
}
