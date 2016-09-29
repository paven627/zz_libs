package test.java.xml.dom4j;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import test.java.xml.Book;

public class Dom4jTest2 {
	static List<Book> books = new ArrayList<Book>();
	
	public static void main(String[] args) throws DocumentException {
		test();
		for (Book book : books) {
			System.out.println(book);
		}
	}
	
	
	private static void test() throws DocumentException{
		File file = new File("src/xml/books.xml");
		SAXReader reader = new SAXReader();
		Document doc = reader.read(file);
		Element root = doc.getRootElement();
		List elements = root.elements("book");
		for (int i = 0; i < elements.size(); i++) {
			Element eleBook = (Element) elements.get(i);
			Book book = new Book();
			books.add(book);
			
			Attribute attribute = eleBook.attribute("category");
			book.setCategory(attribute.getValue());
			Element eleTitle = eleBook.element("title");
			String attrLang = eleTitle.attributeValue("lang");
			book.setLang(attrLang);
			String textTrim = eleTitle.getTextTrim();
			book.setTitle(textTrim);
			List eleAuthors = eleBook.elements("author");
			for (int j = 0; j < eleAuthors.size(); j++) {
				Element eleAuthor = (Element) eleAuthors.get(j);
				String author = eleAuthor.getText();
				book.addAuthor(author);
			}
			Element eleYear = eleBook.element("year");
			book.setYear(Integer.parseInt(eleYear.getText()));
			Element elePrice = eleBook.element("price");
			book.setPrice(Double.parseDouble(elePrice.getText()));
		}
		
		
	}
}
