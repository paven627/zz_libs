package test.java.xml.digester;

import org.apache.commons.digester.Digester;
import java.io.InputStream;

public class BookParseExam {

	public void parse(String filename) throws Exception {
		Digester digester = new Digester();
		/* ��Digester������xml�ĵ���"���/��"Ԫ��ʱ������һ��Bookʵ����󲢽���ѹ�뵽Digester�ڲ�ʹ�õĶ�ջ�С� */
		digester.addObjectCreate("���/��", Book.class);
		// digester.push(new Book());
		/*
		 * ��Digesterע��XML�ĵ��е�XML·�������/��/������Ӧ�õĴ�����򣬵�Digester������xml�ĵ���"���/��/����"Ԫ��ʱ
		 * �� ����Ԫ���е��ı����ݸ���ջ����Bookʵ������bookname���ԡ���ͬ��ķ�ʽ����Bookʵ������author ��price
		 * ���и�ֵ
		 */
		digester.addBeanPropertySetter("���/��/����", "bookname");
		digester.addBeanPropertySetter("���/��/����", "author");
		digester.addBeanPropertySetter("���/��/�ۼ�", "price");
		/* ��Digester������xml�ĵ���"���/��/����"Ԫ�ص�"��λ"����ʱ����������ֵ����ջ����Bookʵ������unit���ԡ� */
		digester.addSetProperties("���/��/�ۼ�", "��λ", "unit");
		InputStream input = this.getClass().getResourceAsStream(filename);
		// ����Digester.parse��������book.xml�ĵ���
		Book book = (Book) digester.parse(input);
		input.close();
		System.out.println(book);
	}

	public static void main(String[] args) throws Exception {
		BookParseExam bookParse = new BookParseExam();
		bookParse.parse("/xml/digester/book.xml");
	}
}
