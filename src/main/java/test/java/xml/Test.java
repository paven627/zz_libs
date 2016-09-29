package test.java.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class Test {
	public static void main(String[] args) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		File file = new File("xml.xml");
		Document doc = builder.parse(file);
		Element root = doc.getDocumentElement();
		NodeList children = root.getChildNodes();
		for (int i = 0, length = children.getLength(); i < length; i++) {
			Node child = children.item(i);
			if (child instanceof Element) {
				Element childElement = (Element) child;
				Text textNode = (Text) childElement.getFirstChild();
				String text = textNode.getData().trim();
				if (childElement.getTagName().equals("name")) {
					System.out.println("name = " + text);
				} else if (childElement.getTagName().equals("size")) {
					System.out.println("size = " + text);
				}
			}
		}
	}
}
