package test.java.io.zip;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 
 */
public class TestZipFile {

	public static void main(String[] args) throws IOException {
		File file = new File("src/main/java/test/java/gui");
		File destFile = new File("d:/home/tmp/zip.zip");
		OutputStream outs = new FileOutputStream(destFile);
		ZipOutputStream zipOutS = new ZipOutputStream(outs);
		TestZipFile test = new TestZipFile();
		test.addFileToZip(zipOutS, file, "");
		System.out.println("OK");
		zipOutS.close();
	}

	public void addFileToZip(ZipOutputStream zipStream, File file, String dirs)
			throws IOException {
		if (file.isDirectory()) {
			File[] subFiles = file.listFiles();
			dirs = dirs.length() == 0 ? "" : dirs + "/";
			for (File file2 : subFiles) {
				addFileToZip(zipStream, file2, dirs + file2.getName());
			}
		} else {
			byte[] buffer = new byte[(int) file.length()];
			InputStream inS = new FileInputStream(file);
			ZipEntry entry = new ZipEntry(dirs);
			zipStream.putNextEntry(entry);
			BufferedInputStream br = new BufferedInputStream(inS);
			while (br.read(buffer) != -1) {
				zipStream.write(buffer);
			}
			inS.close();
		}
	}




}
