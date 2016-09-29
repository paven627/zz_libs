package test.java.io.swq;
import java.io.*;
/**
 * @author 孙卫琴471页
 * 使用缓冲读取文件后直接写入文件操作
 */
public class TestBuffered {
	public static void main(String[] args) {
		File fileIn = new File("D:\\JavaSpace\\练习\\src\\io\\back.java");
		File fileOut = new File("D:\\JavaSpace\\练习\\src\\io\\out.java");
		
		test1(fileIn,fileOut);
		test2(fileIn);
	}
	private static void test1(File fileIn,File fileOut) {
		long t1 =System.currentTimeMillis();
		FileInputStream in = null; 
		FileOutputStream out = null;
		try {
			in = new FileInputStream(fileIn);
			out = new FileOutputStream(fileOut);
			byte[] buff = new byte[(int)fileIn.length()];  // 创建缓冲数组
			int len = in.read(buff);	//将 文件数据 按字节读入缓冲
			while(len!=-1){
				out.write(buff,0,len);    // 将缓冲数据写入文件
//				System.out.println(new String(buff));
				len=in.read(buff);
			}
			in.close();
			out.close();
		} catch (IOException e) {
		}
		long t2=System.currentTimeMillis();
		System.out.println("时间"+(t2-t1));
	}
	
	private static void test2(File file) {
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

}
