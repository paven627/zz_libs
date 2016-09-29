package test.java.io.javass;

import java.io.*;

public class TestSerializable {
	public static void main(String[] args) {
		//writeObject();
		readObject();
	}
	
	private static void readObject() {
		File file = new File("D:\\JavaSpace\\��ϰ\\src\\io\\Temp.java");
		InputStream is = null;
		ObjectInputStream input = null;
		try {
			is = new FileInputStream(file);
			input = new ObjectInputStream(is);
			MyObject o1 = (MyObject)input.readObject();
			System.out.println(o1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���������л�д��temp�ļ�
	 */
	private static void writeObject() {
		File file = new File("D:\\JavaSpace\\��ϰ\\src\\io\\Temp.java");
		MyObject mo =new MyObject();
		OutputStream os = null;
		ObjectOutput out = null;
		try {
			os = new FileOutputStream(file);
			out = new ObjectOutputStream(os);
			out.writeObject(mo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class MyObject implements Serializable{
	String name ="it's Name";
	int i =1;
	@Override
	public String toString(){
		String s = String.format("name==%s , id is %d\n",name,i);
		return s;
	}
}
