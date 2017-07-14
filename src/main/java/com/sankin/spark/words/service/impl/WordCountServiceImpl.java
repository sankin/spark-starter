package com.sankin.spark.words.service.impl;

import java.util.Arrays;

import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.stereotype.Service;

import com.sankin.spark.words.service.WordCountService;
import scala.Tuple2;

@Service
public class WordCountServiceImpl implements WordCountService {

	@Inject
	private JavaSparkContext sc;

	@Override
	public void process(String inputFile, String output) {
		JavaRDD<String> textFile = sc.textFile(inputFile);
		JavaPairRDD<String, Integer> counts = textFile
				.flatMap(s -> Arrays.asList(s.split(" ")).iterator())
				.mapToPair(word -> new Tuple2<>(word, 1))
				.reduceByKey((a, b) -> a + b);
		counts.saveAsTextFile(output + RandomStringUtils.randomAlphabetic(5));
	}
}
