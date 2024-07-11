package com.kh.polymorphism.practice1.model;

import java.util.Arrays;

public class Member {

	private String name;
	private int age;
	private int coupon;
	private Book[] booklist = new Book[2];
	
	public Member() {
	}

	public Member(String name, int age, int coupon, Book[] booklist) {
		super();
		this.name = name;
		this.age = age;
		this.coupon = coupon;
		this.booklist = booklist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getCoupon() {
		return coupon;
	}

	public void setCoupon(int coupon) {
		this.coupon = coupon;
	}

	public Book[] getBooklist() {
		return booklist;
	}

	public void setBooklist(Book[] booklist) {
		this.booklist = booklist;
	}

	@Override
	public String toString() {
		return "Member [name=" + name + ", age=" + age + ", coupon=" + coupon + ", booklist="
				+ Arrays.toString(booklist) + "]";
	}
	
	
}
