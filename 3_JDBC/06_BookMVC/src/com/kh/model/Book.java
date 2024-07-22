package com.kh.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	
	private int bk_no;
	private String bk_title;
	private String bk_author;
	private int bk_price;
	private int bk_pub_no;

}
