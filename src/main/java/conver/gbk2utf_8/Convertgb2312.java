package conver.gbk2utf_8;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import jodd.io.FileNameUtil;
import jodd.io.FileUtil;

/** 
 * 将 GBK 格式源码转成 UTF-8
 * 
 */
public class Convertgb2312 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//GBK编码格式源码路径 
		String srcDirPath = 
 "D:/workspace/projects/zz_libs/src/main/java/test/java/properties";
		//转为UTF-8编码格式源码路径 
		String utf8DirPath = "E:/tmp"; 
		        
		
		//获取所有java文件
		Collection<File> javaGbkFileCol = listAll(srcDirPath);
		        
		for (File javaGbkFile : javaGbkFileCol) { 
		      //UTF8格式文件路径 
		      String utf8FilePath = utf8DirPath+javaGbkFile.getAbsolutePath().substring(srcDirPath.length());
		      //使用GBK读取数据，然后用UTF-8写入数据
		      String path = FileNameUtil.getFullPath(utf8FilePath);
		      File pf = new File(path);
		      if (!pf.exists()) {
		    	  pf.mkdirs();
		      }
		      
		      FileUtil.writeString(utf8FilePath, FileUtil.readString(javaGbkFile, "GB2312"), "UTF-8");
		      //FileUtils.writeLines(new File(utf8FilePath), "UTF-8", FileUtils.readLines(javaGbkFile, "GBK"));        
		}

	}

	public static Collection<File> listAll(String srcDirPath) {
		Collection<File> files = new HashSet<File>();
		File rootDir = new File(srcDirPath);
		if (rootDir.isDirectory()) {
			File[] allFiles = rootDir.listFiles();
			
			for (File file : allFiles) {
				if (file.isDirectory()) {
					files.addAll(listAll(file.getAbsolutePath()));
				} else if (FileNameUtil.getExtension(file.getName()).equalsIgnoreCase("java")) {
					files.add(file);
				}
			}
		} 
		
		return files;
	}
}
