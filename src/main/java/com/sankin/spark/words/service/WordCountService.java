package com.sankin.spark.words.service;


import static org.apache.spark.sql.functions.col;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

import com.sankin.spark.words.model.Word;
import scala.Tuple2;

@Service
public class WordCountService {

	@Inject
	private JavaSparkContext sc;

	@Inject
	private SparkSession sparkSession;

	public Map<String, Integer> reduceByKey(List<String> words) {

		// prep data
		JavaRDD<String> data = sc.parallelize(words);

		// avoid creating a domain object/pojo by using a generic Tuple to group key, count
		JavaPairRDD<String, Integer> pairs = data.mapToPair(s -> new Tuple2(s, 1));

		// execute, collect
		return pairs.reduceByKey((a, b) -> a + b).collectAsMap();
	}

	public Map<String, Long> groupBy(List<String> words) {

		// prep data
		List<Word> data = words
				.stream()
				.map(Word::new)
				.collect(Collectors.toList());

		// setup data set
		Dataset<Row> dataFrame = sparkSession.createDataFrame(data, Word.class);

		// count rows and collect count
		return dataFrame.groupBy(col("word"))
			.count()
			.collectAsList()
			.stream()
			.collect(Collectors.toMap(row -> row.getString(0), row -> row.getLong(1)));

	}

}