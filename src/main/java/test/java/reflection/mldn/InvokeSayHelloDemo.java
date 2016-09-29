package test.java.reflection.mldn;

import java.lang.reflect.Method;

public class InvokeSayHelloDemo {
	public static void main(String args[]) {
		Class<?> c1 = null;
		try {
			c1 = Class.forName("reflection.mldn.Person"); // ʵ��Class����
		} catch (Exception e) {
		}
		try {
			Method met = c1.getMethod("sayHello", String.class, int.class); // �ҵ�sayChina()����
			String rv = null;
			rv = (String) met.invoke(c1.newInstance(), "���˻�", 30); // ���÷���
			System.out.println(rv);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
};