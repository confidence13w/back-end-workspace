package com.kh.example.practice2.controller;

import com.kh.example.practice2.model.Snack;

public class SnackController {

	private Snack s = new Snack();
	
	public String saveData(String kind, String name, String flavor, int numOf, int price) {
		// 데이터를 setter를 이용해 저장하고 "저장 완료되었습니다." 결과를 반환 
		s = new Snack(kind, name, flavor, numOf, price);
		return "저장 완료되었습니다.";
	}
	
	public Snack confirmData() {
		// 저장된 데이터를 반환
		return s;
	}
}
