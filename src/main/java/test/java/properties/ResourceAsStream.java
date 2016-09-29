package test.java.properties;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class ResourceAsStream {

	/**
	 * @param java
	 */
	public static void main(String[] args) {
		readValue("info.properties", "NaMe");
		// readProperties("info.properties" );
		System.out.println("OK");
	}


	public static String readValue(String filePath, String key) {
		Properties props = new Properties();
			InputStream in;
			try {
				in = new BufferedInputStream(new FileInputStream(filePath));
				props.load(in);
				String value = props.getProperty(key);
				return value;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
	}

	//
	public static void readProperties(String filePath) {
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			props.load(in);
			// Enumeration
			Enumeration en = props.propertyNames(); 
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String Property = props.getProperty(key);
				System.out.println(key + Property);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
