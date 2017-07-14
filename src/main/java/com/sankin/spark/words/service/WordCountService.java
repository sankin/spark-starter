package com.sankin.spark.words.service;

import java.util.List;
import java.util.Map;

/**
 * Created by vlad on 7/14/17.
 */
public interface WordCountService {
	Map<String, Integer> reduceByKey(List<String> words);

	Map<String, Long> groupBy(List<String> words);
}
