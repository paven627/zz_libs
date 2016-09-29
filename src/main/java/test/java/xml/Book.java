package test.java.xml;

import java.util.ArrayList;
import java.util.List;

public class Book {

	@Override
	public String toString() {
		return "Book [title=" + title + ", authors=" + authors + ", year="
				+ year + ", price=" + price + ", category=" + category
				+ ", lang=" + lang + "]";
	}

	private String title;
	private List<String> authors = new ArrayList<String>();
	private int year;
	private double price;
	private String category;
	private String lang;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public void addAuthor(String name){
		authors.add(name);
	}

}
