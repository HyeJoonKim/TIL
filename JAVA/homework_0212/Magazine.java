package com.scsa.java.sixth;

public class Magazine {
	private String isbn;
	private String title;
	private String author;
	private String publisher;
	private int year;
	private int month;
	private int price;
	private String desc;
	
	public Magazine(String isbn, String title, String author, String publisher, int price, String desc, int year, int month) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.year = year;
		this.month = month;
		this.price = price;
		this.desc = desc;		
	}
	
	public Magazine(String isbn, String title, String author, String publisher, int price, int year, int month) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.year = year;
		this.month = month;
		this.desc = " ";
	}
	
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		if (isbn.length() == 5)		// 유효성 검사 : 숫자 5자리
			this.isbn = isbn;
	}



	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		if (title != null)		// 유효성 검사 : not null
			this.title = title;
	}



	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		if (author != null)		// 유효성 검사 : not null
			this.author = author;
	}



	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		if (publisher != null)		// 유효성 검사 : not null
			this.publisher = publisher;
	}



	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		if (year > 0)		// 유효성 검사 : 연도 > 0
			this.year = year;
	}



	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		if (month >= 1 && month <= 12)		// 유효성 검사 : 1 <= 월 <= 12
			this.month = month;
	}



	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		if (price > 0)		// 유효성 검사 : 가격 > 0
			this.price = price;
	}



	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		if (desc != null)		// 유효성 검사 : not null
			this.desc = desc;
	}


	public String toString() {
		String s = isbn + "\t| " + title + "\t| " + author + "\t| " + publisher + "\t| "
					+ price + "\t| " + desc + "\t| " + year + "." + month ;
		return s;
	}
}
