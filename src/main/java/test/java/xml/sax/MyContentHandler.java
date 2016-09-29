package test.java.xml.sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import test.java.xml.Book;

/**
 * SAX 的内容处理器
 */
public class MyContentHandler implements ContentHandler {

	private List<Book> books;

	private Book book; // 当前的操作节点代表的对象

	private String currentTag;

	@Override
	public void setDocumentLocator(Locator locator) {
	}

	@Override
	public void startDocument() throws SAXException {
	}

	@Override
	public void endDocument() throws SAXException {
		for (Book book : books) {
			System.out.println(book);
		}
	}

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub

	}

	// Element 开始
	// <book>
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		currentTag = qName;
		if ("bookstore".equals(qName)) {
			books = new ArrayList<Book>();
		}
		if ("book".equals(qName)) {
			book = new Book();
			if (atts != null && atts.getLength() > 0) {
				String category = atts.getValue("category");
				book.setCategory(category);
			}
		}
		if ("title".equals(qName)) {
			if (atts.getLength() > 0) {
				String lang = atts.getValue("lang");
				book.setLang(lang);
			}
		}

	}

	// Element 结束 </book>
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		currentTag = null;
		if ("book".equals(qName)) {
			books.add(book);
		}
	}

	// 标签内的文字
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String text = new String(ch, start, length);
		if ("title".equals(currentTag)) {
			book.setTitle(text);
		} else if ("author".equals(currentTag)) {
			book.addAuthor(text);
		} else if ("year".equals(currentTag)) {
			book.setYear(Integer.parseInt(text));
		} else if ("price".equals(currentTag)) {
			book.setPrice(Double.parseDouble(text));
		}
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// System.out.println(new String(ch,start,length));
	}

	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub

	}
}
