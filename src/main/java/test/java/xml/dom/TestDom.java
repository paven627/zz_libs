package test.java.xml.dom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import test.java.xml.Book;

/**
 * 使用 jdk中的 DOM API 读取books.xml 内容,组装成Book 对象
 * 
 */
public class TestDom {
	static File file = new File("src/xml/books.xml");

	public static void main(String[] args) throws Exception {
		// 读取文件
		// read();
		// 添加节点
		// add();
		// 删除节点
//		delete();
		// 更新节点
//		update();
	}

	private static void update() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		org.w3c.dom.Document doc = builder.parse(file);
		org.w3c.dom.Node eleAuthor = doc.getElementsByTagName("author").item(0);
	
		// eleAuthor.setTextContent("邓斌");
		TransformerFactory fac = TransformerFactory.newInstance();
		fac.newTransformer().transform(new DOMSource(doc),
				new StreamResult("src/xml/dom/books2.xml"));
	}

	private static void delete() throws ParserConfigurationException,
			SAXException, IOException, TransformerConfigurationException,
			TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(file);

		// 得到需要删除的节点
		Element eleYear = (Element) doc.getElementsByTagName("year").item(0);
		// 得到父节点删除自己
		eleYear.getParentNode().removeChild(eleYear);

		TransformerFactory fac = TransformerFactory.newInstance();
		fac.newTransformer().transform(new DOMSource(doc),
				new StreamResult("src/xml/dom/books2.xml"));

	}

	// 添加节点 "售价" <售价>250</售价>
	private static void add() throws SAXException, IOException,
			ParserConfigurationException, TransformerConfigurationException,
			TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(file);
		// 创建新节点
		Element newNode = doc.createElement("新节点");
		newNode.setAttribute("新属性", "属性值");
		// newNode.setTextContent("250");
		// 把节点放在第一本书下
		Element eleBook = (Element) doc.getElementsByTagName("book").item(0);

		Element eleAuthor = (Element) doc.getElementsByTagName("author")
				.item(0);
		eleBook.appendChild(newNode);
		// 新加入节点 放在 eleAuthor 节点之前
		eleBook.insertBefore(newNode, eleAuthor);
		TransformerFactory fac = TransformerFactory.newInstance();
		fac.newTransformer().transform(new DOMSource(eleBook),
				new StreamResult("src/xml/dom/books2.xml"));

	}

	private static void read() throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File("src/xml/books.xml"));
		NodeList list = document.getElementsByTagName("book");
		List<Book> books = new ArrayList<Book>();
		for (int i = 0; i < list.getLength(); i++) {
			Book book = new Book();
			books.add(book);
			Node node = list.item(i);
			NamedNodeMap attributes = node.getAttributes();
			// 属性也是一个节点,取得 category 属性
			Node namedItem = attributes.getNamedItem("category");
			// 属性值
			// String t = namedItem.getTextContent();
			// 或者
			String category = namedItem.getNodeValue();
			book.setCategory(category);
			NodeList childNodes = node.getChildNodes();
			// 循环节点下的子节点,判断属性名称后给对象赋值
			for (int j = 0; j < childNodes.getLength(); j++) {
				// Node childNode = childNodes.item(j);
				// if (childNode instanceof Element) {
				// Element ele = (Element) childNode;
				// System.out.println(ele.getAttribute("lang"));
				// }
				// if (childNode.getNodeName().equals("title")) {
				// NamedNodeMap attrs = childNode.getAttributes();
				// Node lang = attrs.getNamedItem("lang");
				// // title
				// book.setLang(lang.getNodeValue());
				// String txt = childNode.getTextContent();
				// book.setTitle(txt);
				// }
				// if (childNode.getNodeName().equals("author")) {
				// String author = childNode.getTextContent();
				// book.addAuthor(author);
				// }
				// if (childNode.getNodeName().equals("year")) {
				// String year = childNode.getTextContent();
				// book.setYear(Integer.parseInt(year));
				// }
				// if (childNode.getNodeName().equals("price")) {
				// String price = childNode.getTextContent();
				// book.setPrice(Double.parseDouble(price));
				// }
			}
		}
		for (Book book : books) {
			System.out.println(book);
		}
	}
}
