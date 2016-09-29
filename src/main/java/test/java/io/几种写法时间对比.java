package test.java.io;
import java.io.*;

/*
 * 几种写法的对比
 */
public class 几种写法时间对比 {
	public static void main(String[] args) {
		String file1="D:\\JavaSpace\\练习\\src\\io/javass/TestBuffered.java";
		String file2 ="D:\\JavaSpace\\练习\\src\\io/back.java";
		String file3 ="D:\\JavaSpace\\练习\\src\\io/Temp.java";
		File file = new File(file2);
		BufferedOutputStream out = null;
		OutputStream os =null;
//			 os = new FileOutputStream(file3);
			 os = System.out;
			out = new BufferedOutputStream(os);
			
		//带buffered
		testRead(file,out);	
		System.out.println();
		testRead2(file,out);
		System.out.println();
		testRead3(file,out);
		System.out.println();
		testRead4(file,out);
		System.out.println();
		test5(file,out);
	}
	/**FileInputStream*/
	private static void test5(File file,OutputStream out) {
		PrintStream ps = new PrintStream(out);
		System.setOut(ps);
		long t1 = System.currentTimeMillis();
		FileInputStream input;
		try {
			input = new FileInputStream(file);
			byte[] b = new byte[input.available()];
			input.read(b);
			System.out.println(new String(b));
		} catch (Exception e) {
			e.printStackTrace();
		}
		long t2=System.currentTimeMillis();
		System.out.println("test5时间"+(t2-t1));
	}
	
	/** BufferedReader(FileReader) */
	private static void testRead(File file,OutputStream out) {
		PrintStream ps =new PrintStream(out);
		System.setOut(ps);
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
		System.out.printf("testReader1的总共时间为%d\n",(t2-t1));
	}
	/** FileInputStream */
	private static void testRead2(File file,OutputStream out) {
		PrintStream ps = new PrintStream(out);
		System.setOut(ps);
		long t1 = System.currentTimeMillis();
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			byte[] b = new byte[in.available()];
			in.read(b);
			System.out.println(new String(b));
		} catch (IOException e) {
			e.printStackTrace();
		}
		long t2=System.currentTimeMillis();
		System.out.printf("testReader2的总共时间为%d:\n",(t2-t1));
	}
	
	/*   DataInputStream(BufferedInputStream(FileInputStream))   */
	private static void testRead3(File file,OutputStream  out) {
		long t1 = System.currentTimeMillis();
		PrintStream ps = new PrintStream(out);
		PrintStream old = System.out;
		System.setOut(ps);
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
		System.setOut(old);
		System.out.printf("testRead3的时间为%d\n",(t2-t1));
	}

	// BufferedReader(FileReader)
	private static void testRead4(File fileName,OutputStream out) {
		PrintStream ps = new PrintStream(out);
		PrintStream old =System.out;
		System.setOut(ps);
		long t1 = System.currentTimeMillis();
		try {
			BufferedReader br = new BufferedReader(
					new FileReader(fileName)
					);
			String s = null;
			while((s=br.readLine())!= null){
				System.out.println(s);
			}
			br.close();
		} catch (Exception e) {
			System.out.print("无法读取文件");
		}
		long t2 =System.currentTimeMillis();
		System.setOut(old);
		System.out.printf("test4时间%d \n",(t2-t1));
	}

}
