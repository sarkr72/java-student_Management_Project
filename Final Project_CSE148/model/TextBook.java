package model;

import java.io.Serializable;
import java.util.Arrays;

public class TextBook implements Serializable{

	private String bookTitle;
	private String isbn;
	private Name[] author;
	private double price;
	
	public TextBook(String bookTitle, String isbn, Name[] author, double price) {
		super();
		this.bookTitle = bookTitle;
		this.isbn = isbn;
		this.author = author;
		this.price = price;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Name[] getAuthor() {
		return author;
	}

	public void setAuthor(Name[] author) {
		this.author = author;
	}

	public String getPrice() {
		return String.format("%6.2f", price);
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "TextBook [bookTitle=" + bookTitle + ", isbn=" + isbn + ", authors=" + Arrays.toString(author) + ", price=" + getPrice() + "]\n";
	}
	
	
}
