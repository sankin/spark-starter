package com.sankin.spark.words.configuration;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfiguration {

	@Value("${spark.app.name:word-count}")
	private String appName;

	@Value("${spark.master.uri}")
	private String masterUri;

	@Bean
	public SparkConf sparkConf() {
		SparkConf sparkConf = new SparkConf()
				.setAppName(appName)
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
}
