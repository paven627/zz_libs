package test.java.xml.sax;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * sax 基于事件机制编写,可以实现jdk中提供的结构,或者继承DefaultHandler空实现类,有选择的 重写需要使用的方法
 * 
 */
public class SaxJdk {

	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {
		File file = new File("src/xml/books.xml");
		testSaxInJdk(file);
	}

	private static void testSaxInJdk(File file)
			throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		// 解析器
		SAXParser sp = factory.newSAXParser();
		// 读取器
		XMLReader reader = sp.getXMLReader();

		// 设置内容处理器
		reader.setContentHandler(new MyContentHandler());

		// 读取xml文档内容
		reader.parse(new InputSource(new FileInputStream(file)));
	}
}
