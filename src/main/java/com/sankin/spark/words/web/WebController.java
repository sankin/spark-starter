package com.sankin.spark.words.web;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sankin.spark.words.service.WordCountService;

@Controller
public class WebController {

	@Inject
	private WordCountService wordCount;

	@RequestMapping(path = "/api/reduceByKey", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Integer>> words(@RequestParam("data") String data) {
		List<String> words = Arrays.asList(data.split(","));
		return ResponseEntity
				.ok(wordCount.reduceByKey(words));
	}

	@RequestMapping(path = "/api/groupBy", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Long>> sql(@RequestParam("data") String data) {
		List<String> words = Arrays.asList(data.split(","));
		return ResponseEntity
				.ok(wordCount.groupBy(words));
	}
}
