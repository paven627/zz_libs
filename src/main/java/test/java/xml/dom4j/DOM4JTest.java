package test.java.xml.dom4j;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 示例,循环获取节点
 */
public class DOM4JTest {

	public static void main(String[] args) throws DocumentException,
			IOException {
		// testUser();

		// 写出文件
		// createDocument();

		// 解析
		// testFolder();

		test();
	}

	private static void test() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("xmltest.xml"));
		Element root = doc.getRootElement();
//		rootElement.clearContent();
//		System.out.println(root.asXML());
		Element first = (Element) root.elements().get(0);
		System.out.println(first.asXML());
			
		first.getParent().remove(first);
		System.out.println("root: ");
		System.out.println(root.asXML());

	}

	private static void testFolder() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader
				.read(new File("src/xml/dom4j/PLMFolderShare.xml"));
		Element root = doc.getRootElement();
		// 根节点属性
		List<Attribute> attrs = root.attributes();
		for (Attribute attr : attrs) {
			System.out.println("根节点属性: " + attr.getName() + " - "
					+ attr.getValue());
		}
		// 子节点
		Iterator<Element> iter = root.elementIterator();
		while (iter.hasNext()) {
			Element next = iter.next();
			System.out.println("子节点:" + next.getName());
			String valueOf = next.valueOf("@id");
			System.out.println("valueof 获取属性: " + valueOf);

		}
		// xpath 方法
		// 取得第一个 xpath 匹配节点
		Node node = root.selectSingleNode("//class/param-def");
		System.out.println("getName() " + node.getName());
		System.out.println("getPath(root)" + node.getPath(root));
		;
		System.out.println(node.asXML());
		System.out.println(node.getPath());

		// List<Node> params = root.selectNodes("//class/param-def");
		// for (Node eleParam : params) {
		// String descript = eleParam.valueOf("@description");
		// System.out.println("desc:"+ descript);
		// List<Attribute> paramAttrs =
		// for (Attribute paramAttr : paramAttrs) {
		// System.out.println("参数属性: " + paramAttr.getName() + " - "
		// + paramAttr.getValue());
		// }
		// }
	}

	private static void testUser() throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/xml/dom4j/User.hbm.xml"));
		Element rootElement = document.getRootElement();
		System.out.println(rootElement.getName());

		// 循环方式
		for (Iterator<Element> i = rootElement.elementIterator(); i.hasNext();) {
			Element element = i.next();
			System.out.println(element.getName());
			for (Iterator<Attribute> j = element.attributeIterator(); j
					.hasNext();) {
				Attribute attribute = j.next();
				System.out.println(attribute.getName() + "-"
						+ attribute.getValue());
			}
		}
		// xpath 直接指定路径
		List<Node> list = document
				.selectNodes("//hibernate-mapping/class/property/dataType/value");
		for (Node node : list) {
			System.out.println(node.getText());
			Node no = node
					.selectSingleNode("//hibernate-mapping/class/property");
			System.out.println("valueOf:" + no.valueOf("//hibernate-mapping"));
			System.out.println("valueOf:" + no.valueOf("@name"));
		}
	}

	public static Document createDocument() throws IOException {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("hibernate-mapping");
		Element classElement = root.addElement("class")
				.addAttribute("name", "com.bjsxt.User")
				.addAttribute("table", "t_user");
		classElement.addElement("property").addAttribute("name", "username");

		// 美化格式
		OutputFormat format = OutputFormat.createPrettyPrint();

		XMLWriter writer = new XMLWriter(format);
		// XMLWriter writer = new XMLWriter(new
		// FileWriter("src/xml/dom4j/user2.hbm.xml"),
		// format);
		writer.write(document);
		writer.close();

		return document;
	}

}
