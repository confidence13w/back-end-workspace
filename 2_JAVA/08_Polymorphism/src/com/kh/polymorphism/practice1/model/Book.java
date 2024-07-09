package com.kh.polymorphism.practice1.model;

public class Book {
	private String title;
	private boolean coupon;
	private int acessAge;
	
	public Book(String title, boolean coupon, int acessAge) {
		super();
		this.title = title;
		this.coupon = coupon;
		this.acessAge = acessAge;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isCoupon() {
		return coupon;
	}

	public void setCoupon(boolean coupon) {
		this.coupon = coupon;
	}

	public int getAcessAge() {
		return acessAge;
	}

	public void setAcessAge(int acessAge) {
		this.acessAge = acessAge;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", coupon=" + coupon + ", acessAge=" + acessAge + "]";
	}
	
	
}
