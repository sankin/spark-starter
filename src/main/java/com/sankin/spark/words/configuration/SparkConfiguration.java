package com.sankin.spark.words.configuration;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;

@Configuration
public class SparkConfiguration {

	@Value("${spark.app.name:word-count}")
	private String appName;

	@Value("${spark.master.uri:local}")
	private String masterUri;

	@Bean
	public SparkConf sparkConf() {
		return new SparkConf().setAppName(appName).setMaster(masterUri);
	}

	@Bean
	public JavaSparkContext javaSparkContext() {
		AWSCredentials credentials = new DefaultAWSCredentialsProviderChain().getCredentials();
		JavaSparkContext jsc = new JavaSparkContext(sparkConf());
		jsc.hadoopConfiguration().set("fs.s3.awsAccessKeyId", credentials.getAWSAccessKeyId());
		jsc.hadoopConfiguration().set("fs.s3.awsSecretAccessKey", credentials.getAWSSecretKey());
		jsc.hadoopConfiguration().set("fs.s3n.awsAccessKeyId", credentials.getAWSAccessKeyId());
		jsc.hadoopConfiguration().set("fs.s3n.awsSecretAccessKey", credentials.getAWSSecretKey());
		return jsc;
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
