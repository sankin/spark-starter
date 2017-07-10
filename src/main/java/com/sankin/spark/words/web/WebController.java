package com.sankin.spark.words.web;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sankin.spark.words.model.Word;
import com.sankin.spark.words.service.WordCountService;

@Controller
public class WebController {

    @Autowired
	private WordCountService wordCount;

    @RequestMapping(path = "/api/wordcount", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Long>> words(@RequestParam("data") String data) {

		// convert [w1, w2, ..., wN] to List<Words>
        List<Word> words = Arrays
                .stream(data.split(","))
                .map(Word::new)
                .collect(Collectors.toList());

        return ResponseEntity
				.ok(wordCount.count(words));
    }

}
