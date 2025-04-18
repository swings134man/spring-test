package com.lucas.embeddedh2test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class EmbeddedH2TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmbeddedH2TestApplication.class, args);
	}

}
