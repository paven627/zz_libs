package test.java.io.javass;
import java.io.*;

/*
 * ����д���ĶԱ�
 */
public class TestBuffered {
	public static void main(String[] args) {
		String file1="D:\\JavaSpace\\��ϰ\\src\\io/javass/TestBuffered.java";
		String file2 ="D:\\JavaSpace\\��ϰ\\src\\io/back.java";
		File file = new File(file1);
		//��buffered
		System.out.println("**************************************************************************************\n\n\n");
		testRead(file);	
		System.out.println("**************************************************************************************\n\n\n");
		testRead2(file);
		System.out.println("**************************************************************************************\n\n\n");
		testRead3(file);
		//testRead4(file);
	}
	private static void testRead(File file) {
		long t1 =System.currentTimeMillis();
		String currentLine= null ;
		try {
			BufferedReader in = new BufferedReader
				(new FileReader(file));
			char[] c = new char[(int) file.length()];
			in.read(c);
			System.out.println(new String(c));
//			while((currentLine=in.readLine())!=null){
//				System.out.println(currentLine);
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long t2 = System.currentTimeMillis();
		System.out.println("\n testReader1���ܹ�ʱ��Ϊ:"+(t2-t1));
	}
	
	private static void testRead2(File file) {
		long t1 = System.currentTimeMillis();
		try {
			FileInputStream in = new FileInputStream(file);
			byte[] b = new byte[in.available()];
			in.read(b);
			String s = new String(b);
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		long t2=System.currentTimeMillis();
		System.out.println("\n testReader2���ܹ�ʱ��Ϊ:"+(t2-t1));
	}
	
	
	private static void testRead3(File file) {
		long t1 = System.currentTimeMillis();
		String s =null;
		try {
			DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
			while ((s=in.readLine()) != null) {
				System.out.println(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		long t2 = System.currentTimeMillis();
		System.out.println("\n testRead3��ʱ��Ϊ"+(t2-t1));
	}

	private static void testRead4(File file) {
		String s =null;
		try {
			//DataInputStream in = new Data
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
