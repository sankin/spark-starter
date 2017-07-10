package com.sankin.spark.words.service;


import static org.apache.spark.sql.functions.col;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sankin.spark.words.model.Word;

@Service
public class WordCountService {

	@Autowired
	private SparkSession sparkSession;

	public Map<String, Long> count(List<Word> words) {

		Dataset<Row> dataFrame = sparkSession.createDataFrame(words, Word.class);

		return dataFrame.groupBy(col("word"))
				.count()
				.collectAsList()
				.stream()
				.collect(Collectors.toMap(row -> row.getString(0), row -> row.getLong(1)));
	}

}