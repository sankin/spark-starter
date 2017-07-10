package com.sankin.spark.words;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	@Value("${spark.app.name:word-count}")
	private String appName;

	@Value("${spark.home}")
	private String sparkHome;

	@Value("${spark.master.uri:local}")
	private String masterUri;

	@Bean
	public SparkConf sparkConf() {
		SparkConf sparkConf = new SparkConf()
				.setAppName(appName)
				.setSparkHome(sparkHome)
				.setMaster(masterUri);

		return sparkConf;
	}

	@Bean
	public JavaSparkContext javaSparkContext() {
		return new JavaSparkContext(sparkConf());
	}

	@Bean
	public SparkSession sparkSession() {
		return SparkSession
				.builder()
				.sparkContext(javaSparkContext().sc())
				.appName(appName)
				.getOrCreate();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
