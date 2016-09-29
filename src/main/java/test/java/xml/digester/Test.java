package test.java.xml.digester;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

public class Test {

	public void parse(String fileName) throws IOException, SAXException {
		Digester digester = new Digester();
		/** 当解析到xml文档中"书架/书" 元素时,创建一个book实例并将其压入到 Digester 内部的 栈 中 */
		// digester.addObjectCreate("书架/书", Book.class);
		digester.push(new Book());
		/**
		 * 在digester 注册 xml文档中的xml路径"书架/书/书名"所应用的处理规则
		 * 当digester解析到xml文档中"书架/书/书名"元素时
		 * 将该元素中的文本内容赋值给栈顶的book实例对象的,bookName属性,按同样的方式对book实例对象的 author 和
		 * price进行赋值
		 */
		digester.addBeanPropertySetter("书架/书/书名", "bookname");
		digester.addBeanPropertySetter("书架/书/作者", "author");
		digester.addBeanPropertySetter("书架/书/售价", "price");
		/** 当digester解析到xml文档中"书架/书/书名" 元素的"单位"时,将该属性赋值给栈顶的book实例对象的unit属性 */
		digester.addSetProperties("书架/书/售价", "单位", "unit");
		InputStream input = this.getClass().getResourceAsStream(fileName);
		

		/** 调用digester.parse() 方法解析book.xml文档 */
		Book book = (Book) digester.parse(input);
		input.close();
		System.out.println(book);

		// 遇到匹配的 pattern 时,调用上一级对象的 methodName 方法,一般是一个添加方法,将这个对象,添加进上一级对象中
		// digester.addSetNext(pattern, methodName)
		// digester.addCallMethod(pattern, methodName, paramCount);
		// digester.addCallParam(pattern, paramIndex);
	}

	public static void main(String[] args) throws IOException, SAXException {
		Test t = new Test();
		File file = new File("/xml/digester/book.xml");
//		t.parse("/xml/digester/book.xml");
		t.parse("/xml/digester/book.xml");
	}

}
