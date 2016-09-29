package test.java.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * ���͵����Ͳ����ڱ����󼴻ᱻ����,�ƹ������,�� ������ӽ� �ַ�ļ�����
 */
public class Collections {

	public static void main(String[] args) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		List<String> list =new ArrayList<String>();
		List<Integer> list2 = new ArrayList<Integer>();
		System.out.println(list.getClass() == list2.getClass());
//		list.add(5);
		Method method = list.getClass().getMethod("add", Object.class);
		method.invoke(list, 5);
		System.out.println(list);
	}

}
