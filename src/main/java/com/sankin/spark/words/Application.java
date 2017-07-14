package com.sankin.spark.words;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sankin.spark.words.service.WordCountService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Value("${input.file}")
	private String input;

	@Value("${output.location}")
	private String output;

	@Inject
	private WordCountService service;

	@Override
	public void run(String... args) throws Exception {
		service.process(input, output);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
