package com.scsa.java.sixth;

public class Book {
	private String isbn;
	private String title;
	private String author;
	private String publisher;
	private int price;
	private String desc;
	
	
	public Book (String isbn, String title, String author, String publisher, int price, String desc){
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.desc = desc;		
	}
	
	public Book (String isbn, String title, String author, String publisher, int price){
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
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
		String s = isbn + "\t| " + title + "\t| " + author + "\t| " + publisher
					+ "\t| " + price + "\t| " + desc;
		
		return s;
	}
	
}
