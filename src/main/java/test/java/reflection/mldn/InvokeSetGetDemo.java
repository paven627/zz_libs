package test.java.reflection.mldn;

import java.lang.reflect.Method;

public class InvokeSetGetDemo {
	public static void main(String args[]) {
		Class<?> c1 = null;
		Object obj = null;
		try {
			c1 = Class.forName("org.lxh.demo15.Person"); // ʵ��Class����
		} catch (Exception e) {
		}
		try {
			obj = c1.newInstance();
		} catch (Exception e) {
		}
		setter(obj, "name", "���˻�", String.class); // ����setter����
		setter(obj, "age", 30, int.class); // ����setter����
		System.out.print("����");
		getter(obj, "name");
		System.out.print("���䣺");
		getter(obj, "age");
	}

	/**
	 * Object obj��Ҫ�����Ķ��� String att��Ҫ���������� Object value��Ҫ���õ��������� Class<?>
	 * type��Ҫ���õ���������
	 */
	public static void setter(Object obj, String att, Object value,
			Class<?> type) {
		try {
			Method met = obj.getClass().getMethod("set" + initStr(att), type); // �õ�setter����
			met.invoke(obj, value); // ����setter������
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getter(Object obj, String att) {
		try {
			Method met = obj.getClass().getMethod("get" + initStr(att)); // �õ�setter����
			System.out.println(met.invoke(obj)); // ����getterȡ������
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String initStr(String old) { // �����ʵ�����ĸ��д
		String str = old.substring(0, 1).toUpperCase() + old.substring(1);
		return str;
	}
};