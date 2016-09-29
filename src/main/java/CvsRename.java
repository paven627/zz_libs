import java.io.File;


public class CvsRename {

	 
	public static void main(String[] args) {
		 
		File file = new File("E:\\传智播客\\传智播客Ajax");
		rename(file);
		
	}

	
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
