package test.java.reflection.mldn ;
import java.lang.reflect.Array ;
public class ClassArrayDemo{
	public static void main(String args[]) throws Exception{
		int temp[] = {1,2,3} ;// ����һ��������
		Class<?> c = temp.getClass().getComponentType() ;	// ȡ�������Class����
		System.out.println("���ͣ�" + c.getName()) ;	// ȡ�������������
		System.out.println("���ȣ�" + Array.getLength(temp)) ;
		System.out.println("��һ�����ݣ�" + Array.get(temp,0)) ;
		Array.set(temp,0,6) ;
		System.out.println("��һ�����ݣ�" + Array.get(temp,0)) ;
	}
};