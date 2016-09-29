import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class FileRename implements Serializable {
	static String driver = "oracle.jdbc.driver.OracleDriver";
	static String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";

//	static {
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}

	public static void main(String[] args) throws IOException {
		File file = new File("E:\\传智播客\\传智播客Ajax");
		rename(file);
			
	}

	/**
	 * 传智播客文件重命名方法
	 * 
	 * 传智播客的视频文件命名没有编号,而文件夹有编号
	 * 将文件夹下的 .avi 视频命名为视频文件所在文件夹的名字
	 * @param file
	 */
	private static void rename(File file) {
		File[] dirs = file.listFiles();
		for(File dir : dirs){
			if (dir.isDirectory()) {
				String dirName = dir.getName();
				File[] files = dir.listFiles();
				for(File f : files){
					String fileName = f.getName();
					if(fileName.endsWith("avi")){
						System.out.println("文件夹名:"+dirName);
						//父路径
						String parent = f.getParent();
						System.out.println("父路径"+parent);
						System.out.println("fileName :"+fileName);
						
						File newFile = new File(parent+"\\"+dirName+".avi");
						f.renameTo(newFile);
						
					}
				}
			}
		}
	}


}
