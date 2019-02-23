package test.java.io;

import java.io.*;
import java.util.zip.GZIPInputStream;

public class GzipTest {

	public static void main(String[] args) throws Exception {
		File file = new File("C:\\workspace\\test.sh.gz");
		FileInputStream is = new FileInputStream(file);
		String decompress = decompress(is);
		System.out.println(decompress);
	}

	private static String decompress(InputStream is) throws Exception {
		GZIPInputStream gis = new GZIPInputStream(is);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		int count;
		byte data[] = new byte[512];
		while ((count = gis.read(data, 0, 512)) != -1) {
			os.write(data, 0, count);
		}
		gis.close();

		byte[] byteArray = os.toByteArray();
		return new String(byteArray);
	}
}
