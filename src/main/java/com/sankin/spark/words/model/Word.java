package com.sankin.spark.words.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Word implements Serializable {
	private static final long serialVersionUID = -2727638225141870617L;

	private String word;
}
