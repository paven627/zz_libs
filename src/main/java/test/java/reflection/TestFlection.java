package test.java.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestFlection {

	/**
	 * ���÷�����һ����ķ����Ͳ���
	 */
	public static void main(String[] args) {
		String s = "reflection.T";
		Class c;
		try {
			c = Class.forName(s);
			Object o = c.newInstance();
			Method[] methods = c.getMethods();
			for (Method m : methods) {
				//
				if (m.getName().equals("mm")) {
					m.invoke(o); // ���÷���ʱ������Ķ���
				}
				if (m.getName().equals("m1")) {
					m.invoke(o, 1, 2); // �ɱ����
					// ��ӡ�����������
					for (Class paraType : m.getParameterTypes()) {
						System.out.println(paraType.getName());
					}
				}
				// �ҵ�����ֵ������
				if (m.getName().equals("getS")) {
					Class returnType = m.getReturnType();
					System.out.println(returnType);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}

class T {
	String s;
	static {
		System.out.println("T ����T��");
	}

	public T() {
		System.out.println("������");
	}

	public void mm() {
		System.out.println("mm()");
	}

	public void m1(int i, int j) {
		System.out.println(i + j);
	}

	public String getS() {
		return s;
	}
}
