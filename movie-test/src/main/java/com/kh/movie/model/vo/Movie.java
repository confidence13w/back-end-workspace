package com.kh.movie.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class Movie {

	private int id;
	private String title;
	private String genre;
	private String actor;
}
