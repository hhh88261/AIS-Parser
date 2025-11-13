package com.example.aisParsing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;

@SpringBootApplication
@EnableAsync
public class AisParsingApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(AisParsingApplication.class, args);
	}
}