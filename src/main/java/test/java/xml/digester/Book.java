package test.java.xml.digester;

public class Book {
	private String bookname;
	private String author;
	private float price;
	private String unit;

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String toString() {
		return "Book: bookname='" + bookname + "'; author='" + author
				+ "'; price=" + price + unit;
	}
}
