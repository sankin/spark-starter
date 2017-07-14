package com.sankin.spark.words;

import java.util.List;

import javax.inject.Inject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.common.collect.ImmutableList;
import com.sankin.spark.words.service.WordCountService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	// TODO: replace with read from S3
	private static final List<String> words = ImmutableList.of("test", "test", "test", "hello", "there");

	@Inject
	private WordCountService service;

	@Override
	public void run(String... args) throws Exception {

		System.err.println("reduce by key:");
		service.reduceByKey(words)
				.entrySet()
				.forEach(e -> System.err.println(e.getKey() + " , " + e.getValue()));

		System.err.println("group by key:");
		service.groupBy(words)
				.entrySet()
				.forEach(e -> System.err.println(e.getKey() + " , " + e.getValue()));
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
