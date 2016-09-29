package test.java.reflection.mldn ;
import java.lang.reflect.Array ;
public class ChangeArrayDemo{
	public static void main(String args[]) throws Exception{
		int temp[] = {1,2,3} ;// ����һ��������
		int newTemp[] = (int []) arrayInc(temp,5) ;	// ���¿��ٿռ�5
		print(newTemp) ;
		System.out.println("\n-------------------------") ;
		String t[] = {"lxh","mldn","mldnjava"} ;
		String nt[] = (String [])arrayInc(t,8) ;
		print(nt) ;
	}
	public static Object arrayInc(Object obj,int len){
		Class<?> c = obj.getClass() ;
		Class<?> arr = c.getComponentType() ;	// �õ������
		Object newO = Array.newInstance(arr,len) ;	// �����µĴ�С
		int co = Array.getLength(obj) ;
		System.arraycopy(obj,0,newO,0,co) ;	// ��������
		return newO ;
	}
	public static void print(Object obj){	// �������
		Class<?> c = obj.getClass() ;
		if(!c.isArray()){	// �ж��Ƿ�������
			return;
		}
		Class<?> arr = c.getComponentType() ;
		System.out.println(arr.getName()+"����ĳ����ǣ�" + Array.getLength(obj)) ;	 // ���������Ϣ
		for(int i=0;i<Array.getLength(obj);i++){
			System.out.print(Array.get(obj,i) + "��") ;	// ͨ��Array���
		}
	}
};