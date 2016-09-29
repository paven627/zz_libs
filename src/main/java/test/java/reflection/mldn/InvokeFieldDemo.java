package test.java.reflection.mldn ;
import java.lang.reflect.Field ;
public class InvokeFieldDemo{
	public static void main(String args[]) throws Exception{
		Class<?> c1 = null ;
		Object obj = null ;
		c1 = Class.forName("org.lxh.demo15.Person") ;	// ʵ��Class����
		obj = c1.newInstance() ;
		Field nameField = null ;
		Field ageField = null ;
		nameField = c1.getDeclaredField("name") ;	// ȡ��name����
		ageField = c1.getDeclaredField("age") ;	// ȡ��name����
		nameField.setAccessible(true) ;	// �����Զ��ⲿ�ɼ�
		ageField.setAccessible(true) ;	// �����Զ��ⲿ�ɼ�
		nameField.set(obj,"���˻�") ;	// ����name��������
		ageField.set(obj,30) ;			// ����age��������
		System.out.println("����" + nameField.get(obj)) ;
		System.out.println("���䣺" + ageField.get(obj)) ;
	}
};