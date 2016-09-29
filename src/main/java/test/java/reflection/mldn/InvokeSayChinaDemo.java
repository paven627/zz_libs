package test.java.reflection.mldn ;
import java.lang.reflect.Method ;
public class InvokeSayChinaDemo{
	public static void main(String args[]){
		Class<?> c1 = null ;
		try{
			c1 = Class.forName("reflection.mldn.Person") ;	// ʵ��Class����
		}catch(Exception e){}
		try{
			Method met = c1.getMethod("sayChina"); // �ҵ�sayChina()����
			met.invoke(c1.newInstance()) ;	// ���÷���
		}catch(Exception e){
			e.printStackTrace() ;
		}
	}
};