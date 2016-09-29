package test.java.io.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

// 读取资源文件,不重启服务
public class TestProperties {
	public static void main(String[] args) throws IOException {
		// test1();
		test2();
	}

	
	// 服务器环境下再试验 ,classLoader只是读取了资源文件的路径,真正读取文件内容时会再次读取,所以
	// 这种方法每次读取的都是新的文件内容
	private static void test2() throws IOException {
		// 相对于 src 路径下,路径中不能出现中文
		String name = "io/properties/db.properties";

		URL resource = TestProperties.class.getClassLoader().getResource(name);
		System.out.println(resource);
		String path = resource.getPath();
		FileInputStream input = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(input);
		Object object = prop.get("username");
		System.out.println(object);
	}

	// 服务运行时,文件的修改无法获取,因为classLoader 直接读取了资源文件
	private static void test1() throws IOException {
		Properties prop = new Properties();
		InputStream input = TestProperties.class.getClassLoader()
				.getResourceAsStream("io/properties/db.properties");
		prop.load(input);
		Object username = prop.get("username");
		System.out.println(username);
	}
}
